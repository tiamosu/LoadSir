package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.callback.EmptyCallback;
import sample.kingja.loadsir.callback.LoadingCallback;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NormalActivity extends AppCompatActivity {

    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        // Your can change the callback on sub thread directly.
        loadService = LoadSir.Companion.getDefault().register(this, (Callback.OnReloadListener) v -> {
            // Your can change the status out of Main thread.
            new Thread(() -> {
                loadService.showCallback(LoadingCallback.class);
                //do retry logic...
                SystemClock.sleep(500);
                //callback
                loadService.showSuccess();
            }).start();
        }).setCallBack(EmptyCallback.class, (Transport) (context, view, callback) -> {
            if (view != null) {
                TextView mTvEmpty = view.findViewById(R.id.tv_empty);
                mTvEmpty.setText("fine, no data. You must fill it!");
            }
        });
        PostUtil.postCallbackDelayed(loadService, EmptyCallback.class);
    }
}
