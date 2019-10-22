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
import cn.shineiot.libgreendao.helper.GreenDaoHelper;

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

		userDao = GreenDaoHelper.getDaoSession().getUserDao();

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.insert:
				user = new User("张三");
				userDao.insert(user);
				break;
			case R.id.insertall:
				List<User> list = new ArrayList<>();
				for (long i = 1; i < 5; i++) {
					user = new User( "张三-" + i);
					list.add(user);
				}
				userDao.insertInTx(list);

				break;
			case R.id.query:
				//等于=查询.eq
//				QueryBuilder<User> query = userDao.queryBuilder();
//				user = query.where(UserDao.Properties.Name.eq("张三-1")).build().unique();
//				Log.e("tag","id="+user.getId());

				//模糊查询
				QueryBuilder<User> query = userDao.queryBuilder();
				Query<User> userQuery = query.where(UserDao.Properties.Name.like("%"+"张"+"%")).build();
				List<User> userList = userQuery.list();
				//userList.get(0).getStudent().getType();

				Log.e("tag", "id=" + userList.size());
				for(User user1 : userList){
					Log.e("tag",user1.getName()+user1.getId());
				}
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
				if (null != user) {
					user.setName("张赛");
					userDao.update(user);
				}

				break;
			case R.id.updateall:
				List<User> data = new ArrayList<>();
				for (long i = 0; i < 10; i++) {
					user = new User("张三-" + i);
					data.add(user);
				}
				userDao.insertOrReplaceInTx(data);
				break;
			default:
				break;

		}
	}
}
