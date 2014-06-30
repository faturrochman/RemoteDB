package kotakwarna.remotedb.helper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Fajar on 6/26/2014.
 */
public class DialogHelper {

    public ProgressDialog buildProgressDialog (Context c, String message){

        ProgressDialog mProgressDialog;
        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(c);
        mProgressDialog.setMessage(message);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        return mProgressDialog;
    }

    public AlertDialog buildAlertDialogOneButton (Context c, String message, String button_text){

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setCancelable(false);
        builder.setTitle(message);
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton(button_text,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        return alert;
    }
}
