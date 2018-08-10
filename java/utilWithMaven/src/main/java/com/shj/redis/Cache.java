package com.shj.redis;

import java.util.Map;
import java.util.Set;

/**
 * @author wx
 * 对于deal 图片的redis操作
 */
public interface Cache {
   
    public String set(String key, String value);
    public String get(String key);	
	public String getMap(String key, String field);
	public Long setMap(String key, String field, String value);
	public boolean deleteKeyField(String key, String field);
	public Long delKey(String key);
	public boolean isHExists(String key, String field);
	public Map<String, String> getHAll(String key);
	/**
	 * 模糊删除key
	 * @param key
	 * @return
	 */
//	public long deleteLikeKey(String key);
	
	/**
	 * 对某个key设置过期
	 * @param key
	 * @param seconds
	 * @return
	 */
	public boolean setExpire(String key, int seconds);
	
	/**
	 * 查询某个key是否存在
	 * @param key
	 * @return
	 */
	public boolean isExists(String key);
	/**
	 * 保存有序集合
	 * @param info
	 * @return
	 */
	public long zadd(String key, int score, String info);
	public Set zrange(String key);
    /**
     * 有过期时间的设值
     * @param key
     * @param value
     * @param seconds  过期时间
     */
    public boolean setWithExpireTime(String key, String value, int seconds);

    /**
     * 有过期时间的设值
     * @param key
     * @param field
     * @param value
     * @param seconds
     * @return
     */
    public boolean setWithExpireTime(String key, String field, String value, int seconds);

}
