package edu.fresnostate.mail.getthatcheckedout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import Priaid.Diagnosis.Client.DiagnosisClient;
import Priaid.Diagnosis.Model.DiagnosedSpecialisation;
import Priaid.Diagnosis.Model.Gender;
import Priaid.Diagnosis.Model.HealthDiagnosis;
import Priaid.Diagnosis.Model.HealthIssueInfo;
import Priaid.Diagnosis.Model.HealthItem;
import Priaid.Diagnosis.Model.HealthSymptomSelector;
import Priaid.Diagnosis.Model.SelectorStatus;


public class SymptomChecker extends AppCompatActivity {

        private DiagnosisClient _diagnosisClient;

        public static String getProperty(String key,Context context) throws IOException {
            Properties properties = new Properties();;
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
            return properties.getProperty(key);
        }
        @SuppressLint("NewApi")
        public void main(String[] args) {

            String authUrl = "";
            String userName = "";
            String password = "";
            String language = "";
            String healthUrl = "";

            Properties prop = new Properties();
            InputStream input = null;

                try {
                    userName = getProperty("username",getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    password = getProperty("password",getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    authUrl = getProperty("priaid_authservice_url",getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    healthUrl = getProperty("priaid_healthservice_url",getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    language = getProperty("language",getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            CheckRequiredArgs(userName, password, authUrl, healthUrl, language);

            try {
                _diagnosisClient = new DiagnosisClient(userName, password, authUrl, language, healthUrl);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.print(e);
                System.out.print("Nope");
                e.printStackTrace();
            }
        }

        void CheckRequiredArgs(String userName, String password, String authUrl, String healthUrl, String language)
        {
            if ( userName == null || userName.isEmpty())
                throw new IllegalArgumentException("username in config.properties is required");

            if ( password == null || password.isEmpty())
                throw new IllegalArgumentException("password in config.properties is required");

            if (authUrl == null || authUrl.isEmpty())
                throw new IllegalArgumentException("priaid_authservice_url in config.properties is required");

            if (healthUrl == null || healthUrl.isEmpty())
                throw new IllegalArgumentException("priaid_healthservice_url in config.properties is required");

            if (language == null || language.isEmpty())
                throw new IllegalArgumentException("language in config.properties is required");
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        int GetRandom(int maxNumber)
        {
            return ThreadLocalRandom.current().nextInt(0, maxNumber);
        }

        void writeHeaderMessage(String message)
        {
            System.out.println("---------------------------------------------");
            System.out.println(message);
            System.out.println("---------------------------------------------");
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        int loadBodySublocations(int locId) throws Exception
        {
            List<HealthItem> bodySublocations = _diagnosisClient.loadBodySubLocations(locId);

            if (bodySublocations == null || bodySublocations.size() == 0)
                throw new Exception("Empty body sublocations results");

            for (HealthItem loc : bodySublocations)
                System.out.println(loc.Name + " " + loc.ID);

            int randomLocIndex = GetRandom(bodySublocations.size());
            HealthItem randomLocation = bodySublocations.get(randomLocIndex);

            writeHeaderMessage("Sublocations symptoms for selected location " + randomLocation.Name);

            return randomLocation.ID;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        Integer loadBodyLocations() throws Exception{
            List<HealthItem> bodyLocations = _diagnosisClient.loadBodyLocations();

            if (bodyLocations == null || bodyLocations.size() == 0)
                throw new Exception("Empty body locations results");

            writeHeaderMessage("Body locations:");

            for (HealthItem loc : bodyLocations)
                System.out.println(loc.Name + " (" + loc.ID + ")");

            int randomLocIndex = GetRandom(bodyLocations.size());
            HealthItem randomLocation = bodyLocations.get(randomLocIndex);

            writeHeaderMessage("Sublocations for randomly selected location " + randomLocation.Name);

            return randomLocation.ID;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        List<HealthSymptomSelector> LoadSublocationSymptoms(int subLocId) throws Exception
        {
            List<HealthSymptomSelector> symptoms = _diagnosisClient.loadSublocationSymptoms(subLocId, SelectorStatus.Man);

            if (symptoms == null || symptoms.size() == 0)
            {
                System.out.println("Empty body sublocations symptoms results");
                return null;
            }

            writeHeaderMessage("Body sublocations symptoms:");

            for (HealthSymptomSelector sym : symptoms)
                System.out.println(sym.Name);

            int randomSymptomIndex = GetRandom(symptoms.size());

            randomSymptomIndex = GetRandom(symptoms.size());

            HealthSymptomSelector randomSymptom = symptoms.get(randomSymptomIndex);

            writeHeaderMessage("Randomly selected symptom: " + randomSymptom.Name);

            List<HealthSymptomSelector> selectedSymptoms = new ArrayList<HealthSymptomSelector>();
            selectedSymptoms.add(randomSymptom);

            LoadRedFlag(randomSymptom);

            return selectedSymptoms;
        }

        List<Integer> LoadDiagnosis(List<HealthSymptomSelector> selectedSymptoms) throws Exception
        {
            writeHeaderMessage("Diagnosis");

            List<Integer> selectedSymptomsIds = new ArrayList<Integer>();
            for(HealthSymptomSelector symptom : selectedSymptoms){
                selectedSymptomsIds.add(symptom.ID);
            }

            List<HealthDiagnosis> diagnosis = _diagnosisClient.loadDiagnosis(selectedSymptomsIds, Gender.Male, 1988);

            if (diagnosis == null || diagnosis.size() == 0)
            {
                writeHeaderMessage("No diagnosis results for symptom " + selectedSymptoms.get(0).Name);
                return null;
            }

            for (HealthDiagnosis d : diagnosis){
                String specialistions = "";
                for(DiagnosedSpecialisation spec : d.Specialisation)
                    specialistions = specialistions.concat(spec.Name + ", ");
                System.out.println(d.Issue.Name + " - " + d.Issue.Accuracy + "% \nSpecialisations : " + specialistions);
            }

            List<Integer> retValue = new ArrayList<Integer>();
            for(HealthDiagnosis diagnose : diagnosis)
                retValue.add(diagnose.Issue.ID);
            return retValue;
        }


        void LoadRedFlag(HealthSymptomSelector selectedSymptom) throws Exception
        {
            String redFlag = "Symptom " + selectedSymptom.Name + " has no red flag";

            if(selectedSymptom.HasRedFlag)
                redFlag = _diagnosisClient.loadRedFlag(selectedSymptom.ID);

            writeHeaderMessage(redFlag);
        }

        void LoadIssueInfo(int issueId) throws Exception
        {
            HealthIssueInfo issueInfo = _diagnosisClient.loadIssueInfo(issueId);
            writeHeaderMessage("Issue info");
            System.out.println("Name: " + issueInfo.Name);
            System.out.println("Professional Name: " +issueInfo.ProfName );
            System.out.println("Synonyms: " + issueInfo.Synonyms);
            System.out.println("Short Description: " + issueInfo.DescriptionShort );
            System.out.println("Description: " + issueInfo.Description);
            System.out.println("Medical Condition: " + issueInfo.MedicalCondition);
            System.out.println("Treatment Description: " +issueInfo.TreatmentDescription );
            System.out.println("Possible symptoms: " + issueInfo.PossibleSymptoms + "\n");
        }

        void LoadProposedSymptoms(List<HealthSymptomSelector> selectedSymptoms) throws Exception
        {
            List<Integer> selectedSymptomsIds = new ArrayList<Integer>();
            for(HealthSymptomSelector symptom : selectedSymptoms){
                selectedSymptomsIds.add(symptom.ID);
            }
            List<HealthItem> proposedSymptoms = _diagnosisClient.loadProposedSymptoms(selectedSymptomsIds, Gender.Male, 1988);

            if (proposedSymptoms == null || proposedSymptoms.size() == 0)
            {
                writeHeaderMessage("No proposed symptoms for selected symptom " + selectedSymptoms.get(0).Name);
                return;
            }

            String proposed = "";
            for(HealthItem diagnose : proposedSymptoms)
                proposed = proposed.concat(diagnose.Name) + ", ";

            writeHeaderMessage("Proposed symptoms: " + proposed);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        Void simulate(){

            try {
                // Load body locations
                int selectedLocationID = loadBodyLocations();

                // Load body sublocations
                int selectedSublocationID = loadBodySublocations(selectedLocationID);

                // Load body sublocations symptoms
                List<HealthSymptomSelector> selectedSymptoms = LoadSublocationSymptoms(selectedSublocationID);

                // Load diagnosis (reloading if data is not conclusive)
                int count = 0;
                int maxTries = 10;
                boolean sucess = false;
                List<Integer> diagnosis = new ArrayList<Integer>();
                while(sucess!=true) {
                    try {
                        diagnosis = LoadDiagnosis(selectedSymptoms);
                        sucess= true;
                    } catch (Exception diagnosisException){
                        // reload data if diagnosis result is not conclusive
                        selectedLocationID = loadBodyLocations();
                        selectedSymptoms = LoadSublocationSymptoms(selectedSublocationID);
                        if (++count == maxTries) throw diagnosisException;
                        sucess=false;
                    }
                }

                // Load issue info
                for (Integer issueId : diagnosis)
                    LoadIssueInfo(issueId);

                // Load proposed symptoms
                LoadProposedSymptoms(selectedSymptoms);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

    private WebView webview;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker);

/*
        String authUrl = "";
        String userName = "";
        String password = "";
        String language = "";
        String healthUrl = "";

        Properties prop = new Properties();
        InputStream input = null;

        try {
            userName = getProperty("username",getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            password = getProperty("password",getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            authUrl = getProperty("priaid_authservice_url",getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            healthUrl = getProperty("priaid_healthservice_url",getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            language = getProperty("language",getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CheckRequiredArgs(userName, password, authUrl, healthUrl, language);

        try {
            _diagnosisClient = new DiagnosisClient(userName, password, authUrl, language, healthUrl);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.print(e);
            System.out.print("Nope");
            e.printStackTrace();
        }

        int selectedLocationID = 0;
        try {
            selectedLocationID = loadBodyLocations();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load body sublocations
        int selectedSublocationID = 0;
        try {
            selectedSublocationID = loadBodySublocations(selectedLocationID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load body sublocations symptoms
        List<HealthSymptomSelector> selectedSymptoms = null;
        try {
            selectedSymptoms = LoadSublocationSymptoms(selectedSublocationID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count = 0;
        int maxTries = 10;
        boolean sucess = false;
        List<Integer> diagnosis = new ArrayList<Integer>();
        while(sucess!=true) {
            try {
                diagnosis = LoadDiagnosis(selectedSymptoms);
                sucess= true;
            } catch (Exception diagnosisException){
                // reload data if diagnosis result is not conclusive
                try {
                    selectedLocationID = loadBodyLocations();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    selectedSymptoms = LoadSublocationSymptoms(selectedSublocationID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (++count == maxTries) try {
                    throw diagnosisException;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sucess=false;
            }
        }

*/
        //String[] symptomList = selectedSymptoms.toArray();

        String[] symptomList = {"Apple", "Orange"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, symptomList);

        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.symptomInput);
        actv.setThreshold(1);

        actv.setAdapter(adapter);
        actv.setTextColor(Color.RED);

        webview =(WebView)findViewById(R.id.webMD);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl("https://symptomchecker.isabelhealthcare.com/suggest_diagnoses_advanced/landing_page");
    }

}

