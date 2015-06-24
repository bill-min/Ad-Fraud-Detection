package redis;

import org.springframework.data.redis.core.RedisTemplate;

import rest.ClickData;

public class ClickDataStorage {
	private RedisTemplate<String, String> redisTemplate;
	

	public void add(ClickData data) {
		redisTemplate.opsForValue()
				.set("data:" + data.getId(), data.toString());
		redisTemplate.opsForList().leftPush("datas", String.valueOf(data.getId()));
	}

	public String load(int dataId) {
		return redisTemplate.opsForValue().get("data:" + dataId);
	}

	public void delete(int dataId) {
		redisTemplate.delete("data:" + dataId);
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
