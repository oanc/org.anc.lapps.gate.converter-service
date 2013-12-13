package org.anc.lapps.serialization.gate;

import gate.Document;
import gate.Factory;
import gate.creole.ResourceInstantiationException;
import org.lappsgrid.api.*;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.Types;

import org.slf4j.*;

/**
 * @author Keith Suderman
 */
public class GateToJsonConverter extends ConverterBase implements WebService
{
   private static Logger logger = LoggerFactory.getLogger(GateToJsonConverter.class);

   public GateToJsonConverter()
   {

   }

   public long[] produces()
   {
      return new long[] { Types.JSON };
   }

   public long[] requires()
   {
      return new long[] { Types.GATE };
   }

   public Data configure(Data input)
   {
      return DataFactory.error("Unsupported operation.");
   }

   public Data execute(Data input)
   {
      Data result;
      try
      {
         logger.debug("Converting document to JSON");
         Document document = Factory.newDocument(input.getPayload());
         logger.debug("Gate document created.");
         String json = GateSerializer.toJson(document);
         logger.debug("Document serialized to JSON.");
         result = new Data(Types.JSON, json);
      }
      catch (ResourceInstantiationException e)
      {
         logger.error("Unable to convert document.", e);
         result = DataFactory.error(e.getMessage());
      }
      logger.debug("Returning JSON document.");
      return result;
   }
}
