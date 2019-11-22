package br.edu.opi.manager.comunication.email.services;

import com.ecwid.maleorang.MailchimpMethod;
import com.ecwid.maleorang.MailchimpObject;
import com.ecwid.maleorang.annotation.*;

/**
 * Custom implementation for /automations/{workflow_id}/emails/{workflow_email_id}/queue method.
 */
@Method(httpMethod = HttpMethod.POST, version = APIVersion.v3_0, path = "/automations/{workflow_id}/emails/{workflow_email_id}/queue")
public class WorkFlowEmailRequest extends MailchimpMethod<WorkFlowEmailRequest.WorkFlowEmailResponse> {
	/**
	 * This param will be included into the endpoint path.
	 */
	@PathParam
	public final String workflow_id;

	/**
	 * This param will be included into the endpoint path.
	 */
	@PathParam
	public final String workflow_email_id;

	/**
	 * This param will be included into the request body.
	 */
	@Field
	public final String email_address;

	public WorkFlowEmailRequest(String workflowId, String workflowEmailId, String email) {
		this.workflow_id = workflowId;
		this.workflow_email_id = workflowEmailId;
		this.email_address = email;
	}

	public static class WorkFlowEmailResponse extends MailchimpObject {
	}

}
