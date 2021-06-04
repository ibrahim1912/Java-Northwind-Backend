package kodlamaio.northwind.core.utilities.results;

public class ErrorResult extends Result{

	public ErrorResult() { // işlem sonucu başarısız ama mesaj döndürmek istemiyoruz
		super(false);
	}

	public ErrorResult(String message) { // işlem sonucu başarısız mesaj döndürmek istiyoruz
		super(false,message);
	}
}
