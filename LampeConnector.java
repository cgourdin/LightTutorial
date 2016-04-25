/**
 * Copyright (c) 2016 Inria
 *  
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * - Philippe Merle <philippe.merle@inria.fr>
 *
 * Generated at Thu Apr 21 15:16:01 CEST 2016 from platform:/resource/light/model/light.occie by org.occiware.clouddesigner.occi.gen.connector
 */
package light.connector;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.occiware.light.LightClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import light.State;

/**
 * Connector implementation for the OCCI kind:
 * - scheme: http://occiware.org/light#
 * - term: lampe
 * - title: A light resource
 */
public class LampeConnector extends light.impl.LampeImpl
{
	/**
	 * Initialize the logger.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(LampeConnector.class);
	
	private LightClient lightClient = new LightClient("ws://localhost:8025/websocket/light");
	
	/**
	 * Constructs a lampe connector.
	 */
	LampeConnector()
	{
		LOGGER.debug("Constructor called on " + this);
	}

	//
	// OCCI CRUD callback operations.
	//

	/**
	 * Called when this Lampe instance is completely created.
	 */
	@Override
	public void occiCreate()
	{
		LOGGER.debug("occiCreate() called on " + this);
		// Create a light with the light api.
		try {
			lightClient.createLight(this.getId(), this.getSummary());
			// Default state to off.
			this.setState(State.OFF);
		} catch (IOException | TimeoutException ex) {
			LOGGER.error("Error while creating a light : " + ex.getMessage());
		}
	}
	

	/**
	 * Called when this Lampe instance must be retrieved.
	 */
	@Override
	public void occiRetrieve()
	{
		LOGGER.debug("occiRetrieve() called on " + this);
		// TODO : This method will be used to update an entity from his real kind.
//		try {
//			lightClient.find(this.getId());
//		} catch (IOException | TimeoutException ex) {
//			LOGGER.error("Error while finding a light : " + ex.getMessage());
//		}
	}

	/**
	 * Called when this Lampe instance is completely updated.
	 */
	@Override
	public void occiUpdate()
	{
		LOGGER.debug("occiUpdate() called on " + this);
		try {
			lightClient.updateLightLocation(this.getId(), this.getSummary());
		} catch (IOException | TimeoutException ex) {
			LOGGER.error("Error while removing a light : " + ex.getMessage());
		}
	}

	/**
	 * Called when this Lampe instance will be deleted.
	 */
	@Override
	public void occiDelete()
	{
		LOGGER.debug("occiDelete() called on " + this);
		try {
			lightClient.deleteLight(this.getId());
		} catch (IOException | TimeoutException ex) {
			LOGGER.error("Error while removing a light : " + ex.getMessage());
		}
	}

	//
	// Lampe actions.
	//

	/**
	 * Implement OCCI action:
     * - scheme: http://occiware.org/light/lampe/action#
     * - term: switchOn
     * - title: Turn on the light
	 */
	@Override
	public void switchOn()
	{
		LOGGER.debug("Action switchOn() called on " + this);
		try {
			lightClient.switchOn(this.getId());
			this.setState(State.ON);
			
		} catch (IOException | TimeoutException ex) {
			String location = this.getSummary();
			LOGGER.error("Error while turning ON the light " + this.getId() + " on location : " + location + " , message: " + ex.getMessage());
		}
	}

	/**
	 * Implement OCCI action:
     * - scheme: http://occiware.org/light/lampe/action#
     * - term: switchOff
     * - title: Turn off the light
	 */
	@Override
	public void switchOff()
	{
		LOGGER.debug("Action switchOff() called on " + this);
		try {
			lightClient.switchOff(this.getId());
			this.setState(State.OFF);
			
		} catch (IOException | TimeoutException ex) {
			String location = this.getSummary();
			LOGGER.error("Error while turning OFF the light " + this.getId() + " on location : " + location + " , message: " + ex.getMessage());
		}
		
	}

}	
