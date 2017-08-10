package net.callofdroidy.pictureoftheday.presenter;

import android.util.Log;

import net.callofdroidy.pictureoftheday.Constant;
import net.callofdroidy.pictureoftheday.model.NasaPicture;
import net.callofdroidy.pictureoftheday.R;
import net.callofdroidy.pictureoftheday.view.ViewActMain;
import net.callofdroidy.pictureoftheday.webservice.NasaService;
import net.callofdroidy.pictureoftheday.webservice.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterPicture {
    private static final String TAG = "PresenterPic";
    private NasaService nasaService = ServiceGenerator.createService(NasaService.class);

    private ViewActMain viewActMain;

    private String imageUrl;

    public PresenterPicture(ViewActMain viewActMain){
        this.viewActMain = viewActMain;
    }

    public void getNasaPicture(){
        nasaService.getNasaPicture(Constant.apiKey).enqueue(new Callback<NasaPicture>() {
            @Override
            public void onResponse(Call<NasaPicture> call, Response<NasaPicture> response) {
                viewActMain.getIdlingResource().setIdleState(true);
                Log.d(TAG, "onResponse code: " + response.code());
                if(response.code() == 200 || response.code() == 202){
                    // normal process
                    imageUrl = response.body().getUrl();
                    if(imageUrl == null)
                        imageUrl = "";
                    viewActMain.showPicture(imageUrl);

                    if(response.body().getTitle() != null)
                        viewActMain.showPicTitle(response.body().getTitle());
                    else
                        viewActMain.showPicTitle("");
                }else {
                    // display error message
                    viewActMain.showErrMsg(viewActMain.getContext().getString(R.string.http_err_msg));
                }
            }

            @Override
            public void onFailure(Call<NasaPicture> call, Throwable t) {
                viewActMain.showErrMsg(t.getMessage());
            }
        });
    }

    public String getUrl(){
        return imageUrl;
    }
}
