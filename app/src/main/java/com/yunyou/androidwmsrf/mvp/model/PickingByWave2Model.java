package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.PickingByWave2Contract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByWaveRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByWaveResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class PickingByWave2Model extends BaseModel implements PickingByWave2Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public PickingByWave2Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<SavePickByWaveResponse> savePickByWave(SavePickByWaveRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .savePickByWave(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }
}