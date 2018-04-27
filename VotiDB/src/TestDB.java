import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {

	public static void main(String[] args) { // Pattern !!!

		String jdbcURL = "jdbc:mysql://localhost/voti?user=root"; // stringa di connessione per collegarsi (&password=root --> ma non l'ho impostata

		try {
			Connection conn = DriverManager.getConnection(jdbcURL); // il DriverManager mi creo un oggetto per me che è
																	// implementazione di Connection ma che io non conosco
			Statement st = conn.createStatement();
			
			String sql = "SELECT nome, voto " + //lo spazio è importante, creerei una query sbagliata altrimenti
					"FROM libretto " + 
					"ORDER BY voto DESC";
			
			ResultSet res = st.executeQuery(sql); //contiene un meccanismo per ottenere i risultati della mia query
			
			while (res.next()) {
				
				String nome = res.getString("nome"); //sono i valori che ho "selezionato" prima
				int voto = res.getInt("voto");
				
				System.out.format("Voto %d dell'esame %s\n", voto, nome);
			}
			st.close(); // distruggo lo statement, dopo aver fatto ciò che mi serve; ora potrei creare altri statement e fare altre query
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
