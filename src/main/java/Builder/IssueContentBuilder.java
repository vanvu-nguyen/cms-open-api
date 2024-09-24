package Builder;

import modal.IssueFields;

public class IssueContentBuilder {
    private IssueFields issueFields;
    public String build(String key, String id, String summary) {
        IssueFields.Project project = new IssueFields.Project(key);
        IssueFields.IssueType issuetype = new IssueFields.IssueType(id);
        IssueFields.Fields fields = new IssueFields.Fields(summary, project, issuetype);
        issueFields = new IssueFields(fields);
        return BodyJSONBuilder.getJSONContent(issueFields);
    }
    public IssueFields getIssueFields() {
        return issueFields;
    }
}
