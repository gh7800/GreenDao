package cn.shineiot.libgreendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author wangs
 */
@Entity
public class User {
	/**
	 * id为主键 自增
	 */
	@Id(autoincrement = true)
	private Long id;

//	@Property(nameInDb = "name")
//	@NotNull
	private String name;

	/**不会生成列*/
//	@Transient
	private String age;

	//唯一的
//	@Unique
//	private int index;

	//@ToOne(joinProperty = "name")
	//private Student student;

	@Generated(hash = 1666193281)
	public User(Long id, String name, String age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Generated(hash = 586692638)
	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
//	public int getIndex() {
//		return this.index;
//	}
//
//	public void setIndex(int index) {
//		this.index = index;
//	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
