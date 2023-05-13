package projeto.springboot.controller;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Retorna o PDF em Byte para download no navegador
	public byte[] gerarRelatorio (List listDados, String relatorio, 
			ServletContext servletContext) throws Exception{
		//Cria lista de dados para o relatorio com nossa de lista de objeto
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDados);
		
		//Carregar o caminho do arquivo Jasper compilado
		String caminhoJasper= servletContext.getRealPath("relatorios") + 
				File.separator + relatorio + ".jasper";
		
		//Carrega o arquivo Jasper passando os dados 
		JasperPrint impressoraJasper = JasperFillManager.fillReport
				(caminhoJasper, new HashMap<>(), jrbcds);
		
		//Exporta para byte[] para realizar download em PDF.
		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}
	
	
}
