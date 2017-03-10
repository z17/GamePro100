package service_client.client;

import lombok.val;
import org.junit.Test;
import service_client.data.request.SubmitRequest;

import static org.junit.Assert.*;

public class ExecutorClientTest {
    private ExecutorClient client = new ExecutorClient();

    @Test
    public void testSubmit() throws Exception {
        val request = new SubmitRequest(1L, "test code");
        val submit = client.submit(request);
        assertNotNull(submit);
    }
}