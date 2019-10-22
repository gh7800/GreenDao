package cn.shineiot.libgreendao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import cn.shineiot.libgreendao.R;
import cn.shineiot.libgreendao.entity.User;
import cn.shineiot.libgreendao.entity.UserDao;
import cn.shineiot.libgreendao.helper.DBHelper;
import cn.shineiot.libgreendao.helper.DBUtil;

/**
 * @author wangs
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btInsert;
    private Button btQuery;
    private Button btDelete;
    private Button btUpdate;
    private Button btInsertAll;
    private Button btDeleteAll;
    private Button btUpdateAll;
    private TextView content;
    private User user;
    private UserDao userDao;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }

    private void initview() {
        btInsert = findViewById(R.id.insert);
        btQuery = findViewById(R.id.query);
        btDelete = findViewById(R.id.delete);
        btUpdate = findViewById(R.id.update);

        btInsertAll = findViewById(R.id.insertall);
        btDeleteAll = findViewById(R.id.deleteall);
        btUpdateAll = findViewById(R.id.updateall);

        content = findViewById(R.id.content);

        btInsert.setOnClickListener(this);
        btInsertAll.setOnClickListener(this);
        btQuery.setOnClickListener(this);
        btDeleteAll.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btUpdateAll.setOnClickListener(this);
        btUpdate.setOnClickListener(this);

        userDao = DBHelper.getDaoSession().getUserDao();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
//				user = new User("张三");
//				userDao.insert(user);
                user = new User();
                user.setName("张三" + n);
                n++;
                DBUtil.getInstance().insert(user);
                break;
            case R.id.insertall:
                List<User> list = new ArrayList<>();
                for (long i = 1; i < 5; i++) {
                    user = new User();
                    user.setName("张三-" + i);
                    list.add(user);
                }
                userDao.insertInTx(list);

                break;
            case R.id.query:
                //等于=查询.eq
                QueryBuilder<User> querys = userDao.queryBuilder();
//				user = querys.where(UserDao.Properties.Name.eq("张三1")).build().unique();//单个
                user = (User) DBUtil.getInstance().query(userDao, UserDao.Properties.Name, "张三5");
                if (null != user) {
                    Log.e("tag----", "id=" + user.getName());
                }

                //模糊查询
                QueryBuilder<User> query = userDao.queryBuilder();
                Query<User> userQuery = query.where(UserDao.Properties.Name.like("%" + "张" + "%")).build();
                List<User> userList = userQuery.list();
                //userList.get(0).getStudent().getType();

                Log.e("tag", "id=" + userList.size());
                for (int i = 0; i < userList.size(); i++) {
                    Log.e("tag-name--", "-----/---" + userList.get(i).getName());
                }

                List<User> userLists = DBUtil.getInstance().queryList_Like(userDao, UserDao.Properties.Name, "3");
                Log.e("tag0----", userLists.size() + "");

                List<User> list1 = DBUtil.getInstance().queryList(userDao);
                Log.e("tag1----", list1.size() + "");

                List<User> list2 = DBUtil.getInstance().queryList_Eq(userDao, UserDao.Properties.Name, "张三1");
                Log.e("tag2----", +list2.size() + "");

                break;
            case R.id.delete:
                if (null != user) {
                    userDao.delete(user);
                }
                break;
            case R.id.deleteall:
                userDao.deleteAll();
                break;
            case R.id.update:
                User user = (User) DBUtil.getInstance().query(userDao, UserDao.Properties.Name, "张三4");
                if (null != user) {
                    Log.e("tag", user.getName());
                    user.setName("张赛");
//                    userDao.update(user);
                    DBUtil.getInstance().update(userDao,user);
                }

                break;
            case R.id.updateall:
                List<User> data = new ArrayList<>();
                for (long i = 0; i < 10; i++) {
                    user = new User();
                    user.setName("张三-" + i);
                    data.add(user);
                }
                userDao.insertOrReplaceInTx(data);
                break;
            default:
                break;

        }
    }
}
