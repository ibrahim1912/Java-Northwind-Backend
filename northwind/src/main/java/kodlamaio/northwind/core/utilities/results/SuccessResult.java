package kodlamaio.northwind.core.utilities.results;

public class SuccessResult extends Result {

	public SuccessResult() { // işlem sonucu başarılı ama mesaj döndürmek istemiyoruz
		super(true);
	}

	public SuccessResult(String message) { // işlem sonucu başarılı mesaj döndürmek istiyoruz
		super(true,message);
	}

	

}
