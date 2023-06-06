package ca.sunlife.poc.boogle.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.sunlife.poc.boogle.constants.Constants;
import ca.sunlife.poc.boogle.response.QueryResponse;
import ca.sunlife.poc.boogle.response.confluence.ConfluenceResponse;
import ca.sunlife.poc.boogle.response.confluence.Links;
import ca.sunlife.poc.boogle.response.confluence.Results;
import ca.sunlife.poc.boogle.response.sharepoint.Cells;
import ca.sunlife.poc.boogle.response.sharepoint.Fields;
import ca.sunlife.poc.boogle.response.sharepoint.GraphqlResponse;
import ca.sunlife.poc.boogle.response.sharepoint.Hits;
import ca.sunlife.poc.boogle.response.sharepoint.Resource;
import ca.sunlife.poc.boogle.response.sharepoint.SharepointResponse;

public class SearchResponseMapper {

	public static List<QueryResponse> mapSharepointResponse(SharepointResponse sharepointResponse) {
		List<QueryResponse> queryResponses = new ArrayList<>();

		queryResponses = sharepointResponse.getPrimaryQueryResult().getRelevantResults().getTable().getRows().stream()
				.map(row -> setSharepointSearchResponse(row.getCells())).collect(Collectors.toList());

		return queryResponses;
	}

	public static List<QueryResponse> mapConfluenceResponse(ConfluenceResponse confluenceResponse) {
		List<QueryResponse> queryResponses = new ArrayList<>();
		queryResponses = confluenceResponse.getResults().stream()
				.map(result -> setConfluenceResponse(result, confluenceResponse.getLinks()))
				.collect(Collectors.toList());
		return queryResponses;
	}

	public static List<QueryResponse> mapGraphqlResponse(GraphqlResponse graphqlResponse) {
		List<QueryResponse> queryResponses = new ArrayList<>();
		queryResponses = graphqlResponse.getValue().stream()
				.flatMap(hitcontainers -> hitcontainers.getHitsContainers().stream())
				.flatMap(hits -> hits.getHits().stream()).map(hit -> setGraphqlResponse(hit))
				.collect(Collectors.toList());
		return queryResponses;
	}

	private static QueryResponse setSharepointSearchResponse(List<Cells> cells) {
		QueryResponse response = new QueryResponse();

		for (Cells cell : cells) {

			switch (cell.getKey()) {
			case Constants.TITLE:
				response.setTitle(cell.getValue());
				break;
			case Constants.PATH:
				response.setPath(cell.getValue());
				break;
			case Constants.SUMMARY:
				response.setSummary(BoogleUtil.parseHtml(cell.getValue()));
				break;
			case Constants.FILETYPE:
				response.setFileType(cell.getValue());
				break;
			case Constants.ISDOCUMENTTYPE:
				response.setDocument(Boolean.valueOf(cell.getValue()));
				break;
			case Constants.SITENAME:
				response.setParentPageName(BoogleUtil.getLastValue(cell.getValue(), Constants.SLASH));
				response.setParentPagePath(cell.getValue());
				break;
			default:
				break;
			}
		}

		return response;
	}

	private static QueryResponse setConfluenceResponse(Results result, Links links) {
		QueryResponse queryResponse = new QueryResponse();
		queryResponse.setTitle(result.getTitle());
		queryResponse.setParentPageName(result.getSpace().getName());
		queryResponse
				.setParentPagePath(BoogleUtil.createUrl(links.getBase(), result.getSpace().getLinks().getWebui()));
		queryResponse.setPath(BoogleUtil.createUrl(links.getBase(), result.getLinks().getWebui()));
		queryResponse.setSummary(BoogleUtil.parseHtml(result.getBody().getView().getValue()));
		if (result.getType().equals(Constants.ATTACHMENT)) {
			queryResponse.setDocument(true);
			queryResponse.setFileType(BoogleUtil.getLastValue(result.getTitle(), Constants.DOT));

		}

		return queryResponse;
	}

	private static QueryResponse setGraphqlResponse(Hits hits) {
		QueryResponse queryResponse = new QueryResponse();
		Resource resource = hits.getResource();
		Fields fields = resource.getFields();
		queryResponse.setSummary(BoogleUtil.parseHtml(hits.getSummary()));
		if (null != fields) {
			queryResponse.setTitle(fields.getTitle());
			queryResponse.setDocument(fields.getIsDocument());
			queryResponse.setPath(fields.getPath());
			queryResponse.setFileType(fields.getFileType());
			queryResponse.setParentPagePath(fields.getSiteName());
			queryResponse.setParentPageName(BoogleUtil.getLastValue(fields.getSiteName(),Constants.SLASH));
		}
		else {
			queryResponse.setTitle(resource.getName());
			queryResponse.setPath(resource.getWebUrl());
			queryResponse.setParentPagePath(resource.getWebUrl());
			queryResponse.setParentPageName(BoogleUtil.getLastValue(resource.getWebUrl(),Constants.SLASH));

			
		}

		return queryResponse;
	}

}
