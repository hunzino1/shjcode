package com.shj.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

@Service
public class CacheImpl implements Cache {

	public CacheImpl() {
	}

	private Logger logger = LoggerFactory.getLogger(CacheImpl.class);

	@Resource
	@Qualifier("指定的redis缓存地址")
    ShardedJedisPool shardedJedisPool;

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	@Autowired
	@Qualifier("指定的redis缓存地址")
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	@Override
	public boolean setExpire(String key, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			long r = jedis.expire(key, seconds);
			if (r > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("" + e.getMessage());
			return false;
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public boolean isExists(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("" + e.getMessage());
			return false;
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public boolean isHExists(String key, String field) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hexists(key, field);
		} catch (Exception e) {
			logger.error("" + e.getMessage());
			return false;
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	@Override
	public String set(String key, String value) {
		ShardedJedis jedis = null;
		String ret = null;
		try {
			jedis = shardedJedisPool.getResource();

			if (jedis != null)
				ret = jedis.set(key, value);
			else
				logger.info("redo redis 连接失败");
		} catch (Exception e) {
			logger.error("" + e.getMessage());
		} finally {
			shardedJedisPool.returnResource(jedis);
		}

		return ret;

	}

	@Override
	public String get(String key) {
		ShardedJedis jedis = null;
		String ret = null;
		try {
			jedis = shardedJedisPool.getResource();

			if (jedis != null)
				ret = jedis.get(key);
			else
				logger.info("redo redis 连接失败");

		} catch (Exception e) {
			logger.error("" + e.getMessage());
		} finally {
			shardedJedisPool.returnResource(jedis);
		}

		return ret;
	}

	@Override
	public String getMap(String key, String field) {
		ShardedJedis jedis = null;
		String ret = null;
		try {

			jedis = shardedJedisPool.getResource();

			if (jedis != null)
				ret = jedis.hget(key, field);
			else
				logger.info("redo redis 连接失败");

		} catch (Exception e) {
			logger.error("" + e.getMessage());
		} finally {
			shardedJedisPool.returnResource(jedis);
		}

		return ret;

	}

	@Override
	public Long setMap(String key, String field, String value) {
		ShardedJedis jedis = null;
		Long ret = null;
		try {
			jedis = shardedJedisPool.getResource();

			if (jedis != null)
				ret = jedis.hset(key, field, value);
			else
				logger.info("redo redis 连接失败");

		} catch (Exception e) {
			logger.error("" + e.getMessage());
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return ret;
	}

	@Override
	public boolean deleteKeyField(String key, String field) {
		ShardedJedis jedis = null;
		Long ret = null;
		try {
			jedis = shardedJedisPool.getResource();
			if (jedis != null) {
				ret = jedis.hdel(key, field);// 返回成功删除的个数
				if (ret > 0)
					return true;
			} else {
				logger.info("redo redis 连接失败");
				return false;
			}

		} catch (Exception e) {
			logger.error("" + e.getMessage());
			return false;
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return false;
	}


	@Override
	public Map<String, String> getHAll(String key) {
		ShardedJedis jedis = null;
		Map<String, String> map = null;
		try {
			jedis = shardedJedisPool.getResource();
			if (jedis != null) {
				map = jedis.hgetAll(key);
			} else {
				logger.info("redo redis 连接失败");
			}

		} catch (Exception e) {
			logger.error("" + e.getMessage());
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return map;
	}

	
	/**
	 * 保存有序集合
	 * @param info
	 * @return
	 */
	@Override
	public long zadd(String key,int score, String info) {
		ShardedJedis jedis = null;
		long ret = 0;
		try {

			jedis = shardedJedisPool.getResource();

			if (jedis != null) {
				ret = jedis.zadd(key, score, info);
			} else {
				logger.info("redo redis 连接失败");
			}

		} catch (Exception e) {
			logger.error("" + e.getMessage());
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return ret;

	}
	
	@Override
	public Set zrange(String key) {
		ShardedJedis jedis = null;
		Set<String> set =null;
		try {
			jedis = shardedJedisPool.getResource();
			if (jedis != null) {
				set = jedis.zrange(key, 0, -1);
			} else {
				logger.info("redo redis 连接失败");
			}
		} catch (Exception e) {
			logger.error("" + e.getMessage());
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return set;
	}
	
    @Override
    public Long delKey(String key) {
    	ShardedJedis shardedJedis = null;
        try {
        	shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.del(key);
        } catch (Exception e) {
            logger.error("redis操作失败...");
            logger.error("get expects error:" + e.getMessage());
            return null;
        } finally {
        	shardedJedisPool.returnResource(shardedJedis);
        }
    }
	
     /**
     * 有过期时间的设值
     * @param key  
     * @param value
     * @param seconds  过期时间
     */
    public boolean setWithExpireTime(String key, String value,int seconds){
    	String ret= this.set(key, value);
    	return this.setExpire(key, seconds);
    }
    
    /**
     * 有过期时间的设值
     * @param key
     * @param field
     * @param value
     * @param seconds
     * @return
     */
    public boolean setWithExpireTime(String key, String field, String value,int seconds){
    	this.setMap(key, field, value);
    	return this.setExpire(key, seconds);
    }
}
