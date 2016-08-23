package fgarcia.test.protocols.server.web;

import com.google.protobuf.ExtensionRegistry;
import fgarcia.test.protocols.avro.PeopleList;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificDatumReader;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.protobuf.ExtensionRegistryInitializer;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Class used to convert avro messages (avro/binary http Content-Type)
 */
public class AvroHttpMessageConverter extends AbstractHttpMessageConverter<PeopleList> {

    private static final MediaType AVRO = new MediaType("avro", "binary");
    private final static ThreadLocal<Encoder> encoderThreadLocal = new ThreadLocal<>();

    public AvroHttpMessageConverter() {this(null);}

    public AvroHttpMessageConverter(ExtensionRegistryInitializer registryInitializer) {
        super(AVRO, MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON);
        if (registryInitializer != null) {
            ExtensionRegistry extensionRegistry = ExtensionRegistry.newInstance();
            registryInitializer.initializeExtensionRegistry(extensionRegistry);
        }
    }

    /**
     * Define supported types
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        Assert.notNull(clazz, "the class must not be null");
        try {
            if (null != SpecificData.get().getSchema(clazz)) {
                return true;
            }
        } catch (Throwable th) {
            return false;
        }
        return false;
    }

    /**
     * Read an http content and convert it to an avro java object.
     * @return a PeopleList.
     */
    @Override
    protected PeopleList readInternal(Class<? extends PeopleList> clazz, HttpInputMessage inputMessage) {
        try {
            Schema schema = SpecificData.get().getSchema(clazz);
            DatumReader<PeopleList> reader = new SpecificDatumReader<>(schema);
            BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(inputMessage.getBody().toString().getBytes(), null);
            return reader.read(null, decoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Write avro object in an http message.
     */
    @Override
    protected void writeInternal(PeopleList message, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Assert.notNull(message, "the object to encode must not be null");
        SpecificData specificData = SpecificData.get();
        Schema schema = specificData.getSchema(PeopleList.class);
        Assert.notNull(schema, "the schema must not be null");
        GenericDatumWriter<PeopleList> writer = new GenericDatumWriter<>(schema);
        EncoderFactory encoderFactory = new EncoderFactory();
        Encoder encoder = encoderFactory
                .validatingEncoder(
                        schema,
                        encoderFactory.binaryEncoder(
                                outputMessage.getBody(), (BinaryEncoder) encoderThreadLocal.get()));
        writer.write(message, encoder);
        encoder.flush();
    }
}

