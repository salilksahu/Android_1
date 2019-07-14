package com.kazontech.kazon;

import android.os.AsyncTask;
import android.util.Log;

class SendMailTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... params) {
        try {
            String param1 = params[0];
            String param2 = params[1];

            String sMsg = "";
            sMsg = "Android ID: " + param1;

            try {
                GMailSender sender = new GMailSender("ipsitasahu2001@gmail.com", "1ruchita123");
                sender.sendMail("This is Subject",
                        sMsg,
                        "ipsitasahu2001@gmail.com",
                        param2);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
            return "Y";
        } catch (Exception e) {
            this.exception = e;

            return "N";
        }
    }

    protected void onPostExecute(String mail) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}