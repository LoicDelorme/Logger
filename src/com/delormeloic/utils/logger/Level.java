package com.delormeloic.utils.logger;

/**
 * This enumeration represents all loggable level.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public enum Level
{
    /**
     * No messages will be display.
     */
	OFF(Integer.MAX_VALUE),

	/**
	 * Only SEVERE messages will be display.
	 */
	SEVERE(1000),

	/**
	 * Only SEVERE and WARNING messages will be display.
	 */
	WARNING(900),

	/**
	 * Only SEVERE, WARNING and INFO messages will be display.
	 */
	INFO(800),

	/**
	 * Only SEVERE, WARNING, INFO and DEBUG messages will be display.
	 */
	DEBUG(700),

	/**
	 * All messages will be display.
	 */
	ALL(Integer.MIN_VALUE);

	/**
	 * The value associate to the level.
	 */
	private final int value;

	/**
	 * Private constructor which create a level.
	 * 
	 * @param value
	 *            The corresponding value.
	 */
	private Level(int value)
	{
		this.value = value;
	}

	/**
	 * Get the value associate to the level.
	 * 
	 * @return The value associate to the level.
	 */
	public int getValue()
	{
		return this.value;
	}
}