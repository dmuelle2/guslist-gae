/*
 * Implementation (server side) of getting the the BlobStore URL address for the 
 * Google web site that will handle uploading the Blob. 
 */

package edu.gac.mcs270.hvidsten.guslistgae.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.gac.mcs270.hvidsten.guslistgae.client.BlobService;

@SuppressWarnings("serial")
public class BlobServiceImpl extends RemoteServiceServlet implements
    BlobService {

  //Start a GAE BlobstoreService session 
  BlobstoreService 
    blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
  
  //Generate a Blobstore Upload URL 
  public String getBlobStoreUploadUrl() {
	// The URL for the BlobService Upload service is returned.
	//  Also, this web page will be structured so that 
	//  the web page "/guslist/uploadservice" will be served to the 
	//  browser once upload is finished. This url is the launch url for 
	//  the HTTP servlet which will be called when
    //    submitting the FormPanel (see web.xml for servlet def)
    return 
      blobstoreService.createUploadUrl("/guslist/uploadservice");
  }

  //Override doGet to serve blobs.  This will be called when an 
  // uploaded file is viewed in the client
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        blobstoreService.serve(blobKey, resp);
  }
}