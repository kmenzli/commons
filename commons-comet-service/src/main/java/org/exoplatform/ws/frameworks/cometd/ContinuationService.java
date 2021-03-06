package org.exoplatform.ws.frameworks.cometd;

/*
 * Copyright (C) 2003-2008 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
import java.util.Collection;
import java.util.Iterator;
import org.mortbay.cometd.ChannelImpl;
import org.mortbay.cometd.continuation.EXoContinuationBayeux;
import org.mortbay.cometd.continuation.EXoContinuationClient;
import dojox.cometd.Client;

/**
 * Created by The eXo Platform SAS.
 * 
 * @author <a href="mailto:vitaly.parfonov@gmail.com">Vitaly Parfonov</a>
 * @version $Id: $
 */

public class ContinuationService
{

   private final EXoContinuationBayeux bayeux;

   /**
    * @param bayeux
    */
   public ContinuationService(EXoContinuationBayeux bayeux)
   {
      this.bayeux = bayeux;
   }

   /**
    * Send individual message to client.
    * 
    * @param eXoId the user ID
    * @param channel the channel you want to send the message. The client must
    *          listen to this channel to receive it
    * @param data the data you want to send to the client
    */
   public void sendMessage(String eXoId, String channel, Object data)
   {
      sendMessage(eXoId, channel, data, null);
   }

   /**
    * Send individual message to client.
    * 
    * @param eXoId the user ID
    * @param channel the channel you want to send the message. The client must
    *          listen to this channel to receive it
    * @param data the data you want to send to the client
    * @param id the id of message if you set null will be generate automatically
    */
   public void sendMessage(String eXoId, String channel, Object data, String id)
   {
      bayeux.sendMessage(eXoId, channel, data, id);
   }

   /**
    * @param exoId the id of client (exoId).
    * @return Get client by eXoId.
    */
   public EXoContinuationClient getClientByExoId(String exoId)
   {
      return bayeux.getClientByEXoId(exoId);
   }

   /**
    * @param id the id of client (cometd id)
    * @return Get client by id (id generated by cometd service).
    */
   public EXoContinuationClient getClient(String id)
   {
      return (EXoContinuationClient) bayeux.getClient(id);
   }

   /**
    * @return all registered client.
    */
   public Collection<Client> getClients()
   {
      return bayeux.getClients();
   }

   /**
    * @param channel the channel id.
    * @return Return true if channel exist else false.
    */
   public boolean hasChannel(String channel)
   {
      return bayeux.hasChannel(channel);
   }

   /**
    * @return timeout of client reconnect.
    */
   public long getTimeout()
   {
      return bayeux.getTimeout();
   }

   /**
    * @param eXoId the client id (as eXoId).
    * @param channel the id of channel.
    * @return true if client subscribe to channel else false.
    */
   public boolean isSubscribe(String eXoId, String channel)
   {
      ChannelImpl channelImpl = bayeux.getChannel(channel);
      Collection<Client> collection = channelImpl.getSubscribers();
      for (Iterator<Client> iterator = collection.iterator(); iterator.hasNext();)
      {
         Client client = (Client) iterator.next();
         if (client instanceof EXoContinuationClient)
         {
            EXoContinuationClient exoClient = (EXoContinuationClient) client;
            if (exoClient.getEXoId().equals(eXoId))
               return true;
         }
      }
      return false;
   }

   /**
    * Send message to all client that listen channel.
    * 
    * @param channel the id of channel that need send message
    * @param data that send
    */
   public void sendBroadcastMessage(String channel, Object data)
   {
      sendBroadcastMessage(channel, data, null);
   }

   /**
    * Send message to all client that listen channel.
    * 
    * @param channel the id of channel that need send message
    * @param data that send
    * @param msgId id of message
    */
   public void sendBroadcastMessage(String channel, Object data, String msgId)
   {
      bayeux.sendBroadcastMessage(channel, data, msgId);
   }

   /**
    * @param eXoId the client id (as eXoId).
    * @return Return userToken for the client.
    */
   public String getUserToken(String eXoId)
   {
      return bayeux.getUserToken(eXoId);
   }

}
