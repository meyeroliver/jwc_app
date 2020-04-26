package za.grounded.jwc_app.services.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.List;

import za.grounded.jwc_app.services.workers.GetProductListWorker;

public class JWCRepository {
    public static final String GET_PRODUCT_LIST = "GET_PRODUCT_LIST";

    private WorkManager workManager;

    public JWCRepository(Application application) {
        this.workManager = WorkManager.getInstance(application);
    }

    public OneTimeWorkRequest getProductListRequest(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest
                .Builder(GetProductListWorker.class)
                .addTag(GET_PRODUCT_LIST)
                .setConstraints(constraints)
                .build();
        return request;
    }

    public LiveData<List<WorkInfo>> getProductList() {
        this.workManager.enqueueUniqueWork(GET_PRODUCT_LIST,
                ExistingWorkPolicy.REPLACE,
                this.getProductListRequest());
        return this.workManager.getWorkInfosForUniqueWorkLiveData(GET_PRODUCT_LIST);
    }
}