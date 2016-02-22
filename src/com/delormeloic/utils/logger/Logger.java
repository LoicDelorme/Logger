package com.delormeloic.utils.logger;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a logger that can be used to log messages using different level.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Logger
{
	/**
	 * The default level.
	 */
	private static Level defaultLevel = Level.DEBUG;

	/**
	 * The default date time formatter.
	 */
	private static DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * The default print stream.
	 */
	private static PrintStream defaultPrintStream = System.err;

	/**
	 * The default print writer.
	 */
	private static PrintWriter defaultPrintWriter = null;

	/**
	 * Set the default level.
	 * 
	 * @param level
	 *            The default level.
	 */
	public static void setDefaultLevel(Level level)
	{
		if (level != null)
		{
			defaultLevel = level;
		}
	}

	/**
	 * Get the default level.
	 * 
	 * @return The default level.
	 */
	public static Level getDefaultLevel()
	{
		return defaultLevel;
	}

	/**
	 * Set the default date time formatter.
	 * 
	 * @param dateTimeFormatter
	 *            The date time formatter.
	 */
	public static void setDefaultDateTimeFormatter(DateTimeFormatter dateTimeFormatter)
	{
		if (dateTimeFormatter != null)
		{
			defaultDateTimeFormatter = dateTimeFormatter;
		}
	}

	/**
	 * Get the default date time formatter.
	 * 
	 * @return The default date time formatter.
	 */
	public static DateTimeFormatter getDefaultDateTimeFormatter()
	{
		return defaultDateTimeFormatter;
	}

	/**
	 * Set the default print stream.
	 * 
	 * @param printStream
	 *            The print stream.
	 */
	public static void setDefaultPrintStream(PrintStream printStream)
	{
		if (printStream != null)
		{
			defaultPrintStream = printStream;
		}
	}

	/**
	 * Get the default print stream.
	 * 
	 * @return The default print stream.
	 */
	public static PrintStream getDefaultPrintStream()
	{
		return defaultPrintStream;
	}

	/**
	 * Set the default print writer.
	 * 
	 * @param printWriter
	 *            The print writer. If printWriter equals to NULL, logger will use the print stream by default.
	 */
	public static void setDefaultPrintWriter(PrintWriter printWriter)
	{
		defaultPrintWriter = printWriter;
	}

	/**
	 * Get the default print writer.
	 * 
	 * @return The default print writer.
	 */
	public static PrintWriter getDefaultPrintWriter()
	{
		return defaultPrintWriter;
	}

	/**
	 * Log a severe message.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void severe(String message)
	{
		log(Level.SEVERE, message);
	}

	/**
	 * Log a severe exception.
	 * 
	 * @param exception
	 *            The exception to log.
	 */
	public static void severe(Exception exception)
	{
		severe(computeExceptionMessage(exception));
	}

	/**
	 * Log a warning message.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void warning(String message)
	{
		log(Level.WARNING, message);
	}

	/**
	 * Log a warning exception.
	 * 
	 * @param exception
	 *            The exception to log.
	 */
	public static void warning(Exception exception)
	{
		warning(computeExceptionMessage(exception));
	}

	/**
	 * Log an info message.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void info(String message)
	{
		log(Level.INFO, message);
	}

	/**
	 * Log an info exception.
	 * 
	 * @param exception
	 *            The exception to log.
	 */
	public static void info(Exception exception)
	{
		info(computeExceptionMessage(exception));
	}

	/**
	 * Log a debug message.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void debug(String message)
	{
		log(Level.DEBUG, message);
	}

	/**
	 * Log a debug exception.
	 * 
	 * @param exception
	 *            The exception to log.
	 */
	public static void debug(Exception exception)
	{
		debug(computeExceptionMessage(exception));
	}

	/**
	 * Compute an exception message.
	 * 
	 * @param exception
	 *            The exception.
	 * @return The computed message.
	 */
	private static String computeExceptionMessage(Exception exception)
	{
		final StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		final String exceptionMessage = stringWriter.toString();
		final String computedExceptionMessage = exceptionMessage.substring(0, exceptionMessage.length() - 2);

		return computedExceptionMessage;
	}

	/**
	 * Log a message according to a specific level.
	 * 
	 * @param level
	 *            The level.
	 * @param message
	 *            The message to log.
	 */
	private static void log(Level level, String message)
	{
		if (level.getValue() >= defaultLevel.getValue())
		{
			log(computeMessage(level, message));
		}
	}

	/**
	 * Compute a message according to its parameters.
	 * 
	 * @param level
	 *            The level.
	 * @param message
	 *            The message.
	 * @return The computed message.
	 */
	private static String computeMessage(Level level, String message)
	{
		final StringBuilder computedMessage = new StringBuilder();
		computedMessage.append("[");
		computedMessage.append(LocalDateTime.now().format(defaultDateTimeFormatter));
		computedMessage.append(" | ");
		computedMessage.append(level.name());
		computedMessage.append("] ");
		computedMessage.append(message);

		return computedMessage.toString();
	}

	/**
	 * Log a message.
	 * 
	 * @param message
	 *            The message to log.
	 */
	private static void log(String message)
	{
		if (isSet(defaultPrintWriter))
		{
			defaultPrintWriter.println(message);
			defaultPrintWriter.flush();
		}
		else
		{
			defaultPrintStream.println(message);
			defaultPrintStream.flush();
		}
	}

	/**
	 * Check if an object is set.
	 * 
	 * @param object
	 *            The object to check.
	 * @return True if the object is set, else False.
	 */
	private static boolean isSet(Object object)
	{
		return (object != null);
	}
}