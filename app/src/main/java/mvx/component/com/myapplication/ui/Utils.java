package mvx.component.com.myapplication.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (null != connectivityManager) {
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        }


        return activeNetwork != null && activeNetwork.isConnected();


    }
}
