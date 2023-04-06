package com.yunyou.androidwmsrf.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.mvp.contract.PickingByWave2Contract;
import com.yunyou.androidwmsrf.mvp.model.api.Api;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByWaveRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByWaveResponse;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class PickingByWave2Presenter extends BasePresenter<PickingByWave2Contract.Model, PickingByWave2Contract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PickingByWave2Presenter(PickingByWave2Contract.Model model, PickingByWave2Contract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void savePickByWave(SavePickByWaveRequest request) {
        mModel.savePickByWave(request)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(Api.MAX_RETRIES, Api.RETRY_DELAY_SECOND))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<SavePickByWaveResponse>(mErrorHandler) {
                    @Override
                    public void onNext(SavePickByWaveResponse response) {
                        if (null == response) {
                            ToastUtils.showShort(R.string.server_response_exception);
                            AppLifecyclesImpl.playSound();
                            return;
                        }
                        if (response.isSuccess()) {
                            ToastUtils.showShort(response.getMsg());
                            mRootView.afterSave();
                            AppLifecyclesImpl.playSuccessSound();
                        } else {
                            mRootView.showMessage(response.getMsg());
                            AppLifecyclesImpl.playSound();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        AppLifecyclesImpl.playSound();
                    }
                });
    }

}