import java.io.*;
class Arquivo
{

	private File arquivo;
	private PrintWriter pw;

	public Arquivo (String nomeArquivo, String localArquivo) throws Exception
	{
		try{
		pw =  new PrintWriter(this.arquivo);
	        }catch (Exception e)
	        {
			}

		arquivo = new File(nomeArquivo, localArquivo);

	}

	public void gravarArquivo(String str)
	{
		if (arquivo.canWrite())
			pw.print(str);

	}

	public void fecharArquivo()
	{
		pw.close();
	}
}