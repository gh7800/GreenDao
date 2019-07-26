package cn.shineiot.libgreendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author wangs
 */
@Entity
public class Student {
	@Id(autoincrement = true)
	private Long id;

	@Property(nameInDb = "name")
	@NotNull
	private String name;

	private String type;

	@Generated(hash = 1049343442)
	public Student(Long id, @NotNull String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Keep
	public Student(String name ,String type){
		this.name = name;
		this.type = type;
	}

	@Generated(hash = 1556870573)
	public Student() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
