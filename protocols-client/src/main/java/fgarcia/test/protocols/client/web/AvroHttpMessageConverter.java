package fgarcia.test.protocols.client.web;

import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.google.protobuf.ExtensionRegistry;
import fgarcia.test.protocols.avro.PeopleList;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
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

public class AvroHttpMessageConverter extends AbstractHttpMessageConverter<PeopleList> {

    public static final MediaType AVRO = new MediaType("avro", "binary");
    private final ExtensionRegistry extensionRegistry = ExtensionRegistry.newInstance();
    private final AvroMapper mapper = new AvroMapper();

    public AvroHttpMessageConverter() {this(null);}

    public AvroHttpMessageConverter(ExtensionRegistryInitializer registryInitializer) {
        super(AVRO, MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON);
        if (registryInitializer != null) {
            registryInitializer.initializeExtensionRegistry(this.extensionRegistry);
        }
    }

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

    @Override
    protected PeopleList readInternal(Class<? extends PeopleList> clazz, HttpInputMessage inputMessage) {
        try {
            Schema schema = SpecificData.get().getSchema(clazz);
            DatumReader<PeopleList> reader = new SpecificDatumReader<PeopleList>(schema);
            BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(inputMessage.getBody(), null);
            return reader.read(null, decoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeInternal(PeopleList message, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        /*Schema schema = SpecificData.get().getSchema(PeopleList.class);
        DatumWriter<PeopleList> writer = new SpecificDatumWriter<>(schema);
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputMessage.getBody(), null);
        writer.write(message, encoder);*/
        SpecificData specificData = SpecificData.get();
        Schema schema = specificData.getSchema(PeopleList.class);
        outputMessage.getBody().write(
                mapper.writer(new AvroSchema(schema)).writeValueAsBytes(message));
    }
}
