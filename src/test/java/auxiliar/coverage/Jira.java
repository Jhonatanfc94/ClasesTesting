package auxiliar.coverage;
/**
import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.joda.time.DateTime;

import java.net.URI;


public class Jira {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "C:/Demo/myTrustStore");

        URI jiraServerUri = URI.create("https://local.jira.com/");

        AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();

        AuthenticationHandler auth = new BasicHttpAuthenticationHandler("username", "password");
        JiraRestClient restClient = factory.create(jiraServerUri, auth);
        IssueRestClient issueClient = restClient.getIssueClient();

        try {
            IssueInputBuilder iib = new IssueInputBuilder();
            iib.setProjectKey("PROJECT-KEY");
            iib.setSummary("Test Summary");
            iib.setIssueType(getIssueType());
            iib.setDescription("Test Description");
            iib.setPriorityId(3L);
            iib.setAssigneeName("Purus");
            iib.setReporterName("Godwin");
            iib.setDueDate(new DateTime());

            iib.setFieldInput(new FieldInput("cust_field_5445", "Custom Value 1"));

            iib.setFieldInput(new FieldInput("cust_field_599", ComplexIssueInputFieldValue.with("value", "Testing")));

            iib.setFieldInput(new FieldInput(IssueFieldId.COMPONENTS_FIELD, "value"));

            IssueInput issue = iib.build();
            BasicIssue issueObj = issueClient.createIssue(issue).claim();

            System.out.println("Issue " + issueObj.getKey() + " created successfully");
        } finally {
            restClient.close();
        }

    }
}
**/