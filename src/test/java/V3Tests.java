import com.metaopsis.icsapi.v3.dom.*;
import com.metaopsis.icsapi.v3.services.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;
import java.io.File;

public class V3Tests {

    private String username = "";
    private String password = "";

    @Test
    public void LoginTest() {
        try {
            Credentials credentials = new Credentials(username, password);
            LoginService loginService = new LoginService(Url.LOGIN_NA);
            User user = loginService.login(credentials);

            System.out.println(user.toString());

            LogoutService logoutService = new LogoutService(Url.LOGOUT_NA);
            logoutService.logout(user);
            assert(user.getUserInfo().getName().equals(username));
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void LookupTest() {
        ArrayList<object> objectArrayList = new ArrayList<object>();


        objectArrayList.add(new object("Thomas/Fld1/91310825-01bd-4ebe-aa7a-ea0fd1a58dd4", Asset.DTEMPLATE));
        objectArrayList.add(new object("Thomas/Fld1/a41f2ead-353b-4a30-83ab-79bbfd4be6b6", Asset.DTEMPLATE));
        try {
            Credentials credentials = new Credentials(username, password);
            LoginService loginService = new LoginService(Url.LOGIN_NA);
            User user = loginService.login(credentials);


            object[] objects = objectArrayList.toArray(new object[0]);
            Lookup lookup = new Lookup();
            lookup.setObjects(objects);
            LookupService lookupService = new LookupService(user);
            Lookup lookup1 = lookupService.doLookup(lookup);

            System.out.println(lookup1.toString());

            LogoutService logoutService = new LogoutService(Url.LOGOUT_NA);
            logoutService.logout(user);
        } catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void LicenseTest() {
        try {
            Credentials credentials = new Credentials(username, password);
            LoginService loginService = new LoginService(Url.LOGIN_NA);
            User user = loginService.login(credentials);

            LicenseService licenseService = new LicenseService(user);
            License license = licenseService.getLicenses();

            System.out.println(license.toString());

            LogoutService logoutService = new LogoutService(Url.LOGOUT_NA);
            logoutService.logout(user);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void ExportTest() {
        try {
            ArrayList<object> objectArrayList = new ArrayList<object>();


            objectArrayList.add(new object("Thomas/Fld1/91310825-01bd-4ebe-aa7a-ea0fd1a58dd4", Asset.DTEMPLATE));
            objectArrayList.add(new object("Thomas/Fld1/a41f2ead-353b-4a30-83ab-79bbfd4be6b6", Asset.DTEMPLATE));

            Credentials credentials = new Credentials(username, password);
            LoginService loginService = new LoginService(Url.LOGIN_NA);
            User user = loginService.login(credentials);
            object[] objects = objectArrayList.toArray(new object[0]);
            Lookup lookup = new Lookup();
            lookup.setObjects(objects);
            LookupService lookupService = new LookupService(user);

            Lookup lookup1 = lookupService.doLookup(lookup);

            ArrayList<RequestObject> requestObjects = new ArrayList<>();
            for (object obj : lookup1.getObjects()) {
                requestObjects.add(new RequestObject(obj.getId(),false));
            }

            ExportRequest exportRequest = new ExportRequest();
            String jobName = "testExport1-" + UUID.randomUUID().toString();
            exportRequest.setName(jobName);
            exportRequest.setObjects(requestObjects.toArray(new RequestObject[0]));

            ExportService exportService = new ExportService(user);
            StartResponse startResponse = exportService.start(exportRequest);
            Status status = null;
            do {
                Thread.sleep(5000);

                status = exportService.status(startResponse.getId(), true);

                System.out.println(status.toString());
            } while(status.getStatus().getState().equals("IN_PROGRESS"));


            exportService.statusLog(startResponse.getId(), new File("/test/V3/"+jobName+".txt"));


            if (status.getStatus().getState().equals("SUCCESSFUL"))
            {
                exportService.download(startResponse.getId(), new File("/test/V3/"+jobName+".zip"));
            }

            LogoutService logoutService = new LogoutService(Url.LOGOUT_NA);
            logoutService.logout(user);

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void ImportTest()
    {
        try {
            Credentials credentials = new Credentials("tbennett@unicosolution.app.com", "Lak3v13w.c0m");
            LoginService loginService = new LoginService(Url.LOGIN_NA);
            User user = loginService.login(credentials);


            ImportService importService = new ImportService(user);
            UploadResponse uploadResponse = importService.upload(new File("/Users/thomasbennett/Downloads/Metaopsis/Archive.zip"), true);

            StartResponse startResponse = importService.start(uploadResponse.getJobId());

            StatusResponse statusResponse = null;
            do {
                Thread.sleep(5000);
                statusResponse = importService.status(startResponse.getId(), false);
            } while(statusResponse.getStatus().getState().equals("IN_PROGRESS"));

            importService.getStatusLog(startResponse.getId(), new File("/Users/thomasbennett/Downloads/Metaopsis/" + startResponse.getName() + ".log"));



            LogoutService logoutService = new LogoutService(Url.LOGOUT_NA);
            logoutService.logout(user);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
