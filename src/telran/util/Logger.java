package telran.util;

import java.lang.System.Logger.Level;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Logger {
	Level level;
	Handler handler;
	String name;
	
	public Logger(Handler handler, String name) {
		this.handler = handler;
		this.name = name;
		this.level = Level.INFO;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	private LoggerRecord getLoggerReccord(String message) {
		ZonedDateTime time = ZonedDateTime.now();
		ZoneId zoneId = time.getZone();
		LoggerRecord record = new LoggerRecord(Instant.now(), zoneId.toString(), level, name, message);
		return record;
	}
	
	public void error(String message) {
		if (level.equals(Level.ERROR)) {
			handler.publish(getLoggerReccord(message));
		}
	}

	
	public void warn(String message) {
		if (level.equals(Level.WARNING)) {
			handler.publish(getLoggerReccord(message));
		}	
	}
	
	public void info(String message) {
		if (level.equals(Level.INFO)) {
			handler.publish(getLoggerReccord(message));
		}
	}
	
	public void debug(String message) {
		if (level.equals(Level.DEBUG)) {
			handler.publish(getLoggerReccord(message));
		}
	}
	
	public void trace(String message) {
		if (level.equals(Level.TRACE)) {
			handler.publish(getLoggerReccord(message));
		}
	}

}
