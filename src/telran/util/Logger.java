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
	
	
	public void error(String message) {
		if (checkLevel(level,Level.ERROR)) {
			handler.publish(getLoggerReccord(message,Level.ERROR));
		}
	}

	public void warn(String message) {
		if (checkLevel(level,Level.WARNING)) {
			handler.publish(getLoggerReccord(message,Level.WARNING));
		}
	}
	
	public void info(String message) {
		if (checkLevel(level,Level.INFO)) {
			handler.publish(getLoggerReccord(message,Level.INFO));
		}
	}
	
	public void debug(String message) {
		if (checkLevel(level,Level.DEBUG)) {
			handler.publish(getLoggerReccord(message,Level.DEBUG));
		}
	}
	
	public void trace(String message) {
		if (checkLevel(level,Level.TRACE)) {
			handler.publish(getLoggerReccord(message, Level.TRACE));
		}
	}
	
	private LoggerRecord getLoggerReccord(String message, Level level) {
		ZonedDateTime time = ZonedDateTime.now();
		ZoneId zoneId = time.getZone();
		LoggerRecord record = new LoggerRecord(Instant.now(), zoneId.toString(), level, name, message);
		return record;
	}
	
	private boolean checkLevel(Level level, Level levelTest) {
		return level.compareTo(levelTest) < 1;
	}

}
