package za.grounded.jwc_app.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.google.android.gms.common.util.Strings;

import java.util.List;
import java.util.concurrent.ExecutionException;

import za.grounded.jwc_app.database.JWCDatabase;
import za.grounded.jwc_app.database.daos.UserDao;
import za.grounded.jwc_app.models.User;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        this.userDao = JWCDatabase.getJwcDatabase(application).userDao();
    }

    public LiveData<List<User>> getLiveUserList() {
        return this.userDao.getLiveUserList();
    }

    public void insertUser(User user) {
        InsertUser task = new InsertUser(this.userDao);
        task.execute(user);
    }

    private static class InsertUser extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public InsertUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            this.userDao.insertCartItem(users[0]);
            return null;
        }
    }

    public List<String> getUsernameList(){
        GetUsernameList task = new GetUsernameList(this.userDao);
        task.execute();
        try {
            return task.get();
        }  catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetUsernameList extends AsyncTask<Void, Void, List<String>> {

        private UserDao userDao;

        public GetUsernameList(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            return this.userDao.getUsernameList();
        }
    }

    public User getUserByUsername(String username){
        GetUserByUsername task = new GetUserByUsername(this.userDao);
        task.execute(username);
        try {
            return task.get();
        }  catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetUserByUsername extends AsyncTask<String, Void, User> {

        private UserDao userDao;

        public GetUserByUsername(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return this.userDao.getUserByUsername(strings[0]);
        }
    }
}
