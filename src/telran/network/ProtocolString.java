package telran.network;

public class ProtocolString implements Protocol{

	@Override
	public Response getResponse(Request request) {
		
		Response response = switch (request.type) {
		case "reverse" -> reverseString(request);					
		case "length" -> lengthString(request);
		default -> new Response(ResponseCode.WRONG_REQUEST, request.data);
		};
	
		return response;
	}

	private Response lengthString(Request request) {
		Response response = new Response(null, request.data);
		try {
			response.data = request.data.toString().length();
			response.code = ResponseCode.OK;
		} catch (Exception e) {
			response.code = ResponseCode.WRONG_DATA;
		}
			
		return response;
	}

	private Response reverseString(Request request) {
		Response response = new Response(null, request.data);
		try {
			response.data = new StringBuilder(request.data.toString()).reverse().toString();
			response.code = ResponseCode.OK;
		} catch (Exception e) {
			response.code = ResponseCode.WRONG_DATA;
		}
		return response;
	}

	
}
