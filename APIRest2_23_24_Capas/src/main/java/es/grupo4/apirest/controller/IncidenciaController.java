package es.grupo4.apirest.controller;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import es.grupo4.apirest.Dto.IncidenciaDto;
import es.grupo4.apirest.Dto.IncidenciaFilterDto;
import es.grupo4.apirest.enums.Perfil;
import es.grupo4.apirest.model.Personal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.service.IncidenciaService;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {

	@Autowired
	private IncidenciaService incidenciaService;
	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("/incidencias")
	public List<IncidenciaDto> getIncidencias() {
		return incidenciaService.getIncidencias();
	}

	@GetMapping("/filtro")
	public List<IncidenciaDto> getIncidenciasByFiltro(
			@RequestBody IncidenciaFilterDto filtro,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaCreacion) {
	    return incidenciaService.getByTipoAndIncidenciasSubtipoAndEstado(filtro);
	}

	@GetMapping("/por-id/{id}")
	public IncidenciaDto getIncidenciaById(@PathVariable int id){
		return IncidenciaDto.fromEntity(incidenciaService.getById(id));

	}

	@GetMapping("/incidencias/{creadorId}")
	public List<IncidenciaDto> getIncidenciasByCreador(@PathVariable int creadorId) {
		return incidenciaService.getIncidenciasByCreadorId(creadorId);
	}
	
	@PostMapping("/save")
	public void saveIncidencia(@RequestBody IncidenciaDto incidenciaDto) {
		incidenciaService.saveIncidencia(incidenciaDto);
	}

	@PutMapping(path = "update/")
	public void updateIncidenciaById(@RequestBody IncidenciaDto incidenciaDto) {
		incidenciaService.updateById(incidenciaDto);
	}

	@PostMapping("/asignarincidencia")
	public String asignarIncidencia(@RequestParam int idUsuario, @RequestParam int idIncidencia) {

		incidenciaService.asignarIncidencia(idUsuario, idIncidencia);

		return "Incidencia asignada correctamente al usuario con ID: " + idUsuario;
	}


	@DeleteMapping(path = "delete/{id}")
	public String deleteIncidenciaById(@PathVariable("id") int id) {
		boolean deleted = incidenciaService.deletedIncidencia(id);
		if (deleted) {
			return "Incidencia con id " + id + " eliminado correctamente";
		} else {
			return "Error al eliminar aula";
		}
	}
	@GetMapping("/abiertas")
	public List<IncidenciaDto> getIncidenciasAbiertas(){
		return incidenciaService.getIncidenciasAbiertas();
	}

	@GetMapping(value = "/generarexcel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateExcel() {
		List<Incidencia> lista = incidenciaService.getIncidenciasSinDTO();

		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet("Incidencias");

			XSSFRow headerRow = sheet.createRow(0);
			List<String> headers = List.of("Num", "Adjunto URL", "Descripcion", "Estado", "Fecha Cierre",
					"Fecha Creacion", "Tipo", "Tiempo DEC", "Equipo", "Subtipo", "Creador", "Responsable");

			for (int i = 0; i < headers.size(); i++) {
				headerRow.createCell(i).setCellValue(headers.get(i));
				sheet.autoSizeColumn(i, true);
			}

			for (int f = 0; f < lista.size(); f++) {
				Incidencia incidencia = lista.get(f);
				XSSFRow row = sheet.createRow(f + 1);

				row.createCell(0, CellType.NUMERIC).setCellValue(incidencia.getNum());
				row.createCell(1, CellType.STRING).setCellValue(incidencia.getAdjuntoUrl());
				row.createCell(2, CellType.STRING).setCellValue(incidencia.getDescripcion());
				row.createCell(3, CellType.STRING).setCellValue(incidencia.getEstado());
				LocalDateTime fechaCierre = incidencia.getFechaCierre();
				row.createCell(4, CellType.STRING).setCellValue(fechaCierre != null ? fechaCierre.toString() : "");
				row.createCell(5, CellType.STRING).setCellValue(incidencia.getFechaCreacion().toString());
				row.createCell(6, CellType.STRING).setCellValue(incidencia.getTipo());
				Time tiempoDec = incidencia.getTiempo_dec();
				row.createCell(7, CellType.STRING).setCellValue(tiempoDec != null ? tiempoDec.toString() : "");

				String tipoEquipo = incidencia.getEquipo() != null ? incidencia.getEquipo().getTipoEquipo().toString()
						: "";
				row.createCell(8, CellType.STRING).setCellValue(tipoEquipo);
				String subtipoNombre = incidencia.getIncidenciasSubtipo() != null
						? incidencia.getIncidenciasSubtipo().getSubtipoNombre()
						: "";
				row.createCell(9, CellType.STRING).setCellValue(subtipoNombre);
				String responsableNombre = incidencia.getPersonal2() != null
						? incidencia.getPersonal2().getNombre() + " " + incidencia.getPersonal2().getApellido1()
						: "";
				String creadorNombre = incidencia.getPersonal1() != null
						? incidencia.getPersonal1().getNombre() + " " + incidencia.getPersonal1().getApellido1()
						: "";
				row.createCell(10, CellType.STRING).setCellValue(creadorNombre);

				row.createCell(11, CellType.STRING).setCellValue(responsableNombre);
			}
			String filePath = "./src/main/resources/inicidencias.xlsx";

			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
				workbook.write(fileOut);
			} catch (IOException e) {
				throw new RuntimeException("Error al escribir el archivo Excel en la carpeta del proyecto", e);
			}

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=incidencias.xlsx");

			byte[] excelContent;
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				workbook.write(out);
				excelContent = out.toByteArray();
			} catch (IOException e) {
				throw new RuntimeException("Error al convertir el archivo Excel a bytes", e);
			}

			return ResponseEntity.ok().headers(responseHeaders).contentLength(excelContent.length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excelContent);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo Excel", e);
		}
	}

	@GetMapping(value = "/generar-excel-incidencias-abiertas", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateExcelIncidenciasCerradas() {
		List<Incidencia> lista = incidenciaService.getByEstado("abierta");
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet("Incidencias");

			XSSFRow headerRow = sheet.createRow(0);
			List<String> headers = List.of("Num", "Adjunto URL", "Descripcion", "Estado", "Fecha Cierre", "Fecha Creacion",
					"Tipo", "Tiempo DEC", "Equipo", "Subtipo", "Creador", "Responsable");

			for (int i = 0; i < headers.size(); i++) {
				headerRow.createCell(i).setCellValue(headers.get(i));
				sheet.autoSizeColumn(i, true);
			}

			for (int f = 0; f < lista.size(); f++) {
				Incidencia incidencia = lista.get(f);
				XSSFRow row = sheet.createRow(f + 1);

				row.createCell(0, CellType.NUMERIC).setCellValue(incidencia.getNum());
				row.createCell(1, CellType.STRING).setCellValue(incidencia.getAdjuntoUrl());
				row.createCell(2, CellType.STRING).setCellValue(incidencia.getDescripcion());
				row.createCell(3, CellType.STRING).setCellValue(incidencia.getEstado());
				LocalDateTime fechaCierre = incidencia.getFechaCierre();
				row.createCell(4, CellType.STRING).setCellValue(fechaCierre != null ? fechaCierre.toString() : "");
				row.createCell(5, CellType.STRING).setCellValue(incidencia.getFechaCreacion().toString());
				row.createCell(6, CellType.STRING).setCellValue(incidencia.getTipo());
				Time tiempoDec = incidencia.getTiempo_dec();
				row.createCell(7, CellType.STRING).setCellValue(tiempoDec != null ? tiempoDec.toString() : "");
				String tipoEquipo = incidencia.getEquipo() != null ? incidencia.getEquipo().getTipoEquipo().toString() : "";
				row.createCell(8, CellType.STRING).setCellValue(tipoEquipo);
				String subtipoNombre = incidencia.getIncidenciasSubtipo() != null
						? incidencia.getIncidenciasSubtipo().getSubtipoNombre()
						: "";
				row.createCell(9, CellType.STRING).setCellValue(subtipoNombre);
				String responsableNombre = incidencia.getPersonal2() != null
						? incidencia.getPersonal2().getNombre() + " " + incidencia.getPersonal2().getApellido1()
						: "";
				String creadorNombre = incidencia.getPersonal1() != null
						? incidencia.getPersonal1().getNombre() + " " + incidencia.getPersonal1().getApellido1()
						: "";
				row.createCell(10, CellType.STRING).setCellValue(creadorNombre);

				row.createCell(11, CellType.STRING).setCellValue(responsableNombre);
			}
			String filePath = "./src/main/resources/inicidencias_abiertas_admin.xlsx";

			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
				workbook.write(fileOut);
			} catch (IOException e) {
				throw new RuntimeException("Error al escribir el archivo Excel en la carpeta del proyecto", e);
			}

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=incidencias_resueltas_admin.xlsx");

			byte[] excelContent;
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				workbook.write(out);
				excelContent = out.toByteArray();
			} catch (IOException e) {
				throw new RuntimeException("Error al convertir el archivo Excel a bytes", e);
			}

			return ResponseEntity.ok().headers(responseHeaders).contentLength(excelContent.length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excelContent);
		}catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo Excel con el gráfico", e);
		}
	}

	@GetMapping(value = "/generar-excel-estadistica-por-tipo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateExcelEstadisticaPorTipo() {
		try {
			TypedQuery<Object[]> query = entityManager
					.createQuery("SELECT tipo, COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Incidencia) AS porcentaje "
							+ "FROM Incidencia GROUP BY tipo", Object[].class);

			List<Object[]> estadisticasPorTipo = query.getResultList();

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Estadisticas Por Tipo");

			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Tipo");
			headerRow.createCell(1).setCellValue("Porcentaje");

			for (int f = 0; f < estadisticasPorTipo.size(); f++) {
				Row row = sheet.createRow(f + 1);
				Object[] estadistica = estadisticasPorTipo.get(f);
				row.createCell(0).setCellValue(estadistica[0].toString());
				row.createCell(1).setCellValue(Double.parseDouble(estadistica[1].toString()));
			}

			Drawing<?> drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 2, 0, 12, 15);

			XDDFChart chart = createBarChart(workbook, estadisticasPorTipo, anchor);
			XDDFChartLegend legend = chart.getOrAddLegend();
			legend.setPosition(LegendPosition.RIGHT);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=inicidencias_con_grafico.xlsx");

			byte[] excelContent;
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				workbook.write(out);
				excelContent = out.toByteArray();
			} catch (IOException e) {
				throw new RuntimeException("Error al convertir el archivo Excel a bytes", e);
			}

			return ResponseEntity.ok().headers(responseHeaders).contentLength(excelContent.length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excelContent);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo Excel con el gráfico", e);
		}
	}

	private XDDFChart createBarChart(Workbook workbook, List<Object[]> data, ClientAnchor anchor) {
		XSSFDrawing drawing = (XSSFDrawing) workbook.getSheetAt(0).createDrawingPatriarch();
		XDDFChart chart = drawing.createChart(anchor);

		XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
		bottomAxis.setTitle("Tipos");

		XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
		leftAxis.setTitle("Porcentaje");

		XDDFDataSource<String> category = XDDFDataSourcesFactory.fromStringCellRange((XSSFSheet) workbook.getSheetAt(0),
				new CellRangeAddress(1, data.size(), 0, 0));

		XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory
				.fromNumericCellRange((XSSFSheet) workbook.getSheetAt(0), new CellRangeAddress(1, data.size(), 1, 1));

		XDDFChartData data1 = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
		data1.setVaryColors(true);

		XDDFChartData.Series series = data1.addSeries(category, values);
		series.setTitle("Porcentaje", null);

		chart.plot(data1);

		return chart;
	}

	@GetMapping(value = "/generar-excel-estadistica-tiempo-por-tipo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateExcelEstadisticaTiempoPorTipo() {
		try {
			TypedQuery<Object[]> query = entityManager.createQuery(
					"SELECT tipo, CAST(AVG(CAST(tiempo_dec AS DOUBLE)) AS DOUBLE) AS tiempo_promedio FROM Incidencia GROUP BY tipo",
					Object[].class);

			List<Object[]> estadisticasTiempoPorTipo = query.getResultList();

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Estadisticas Tiempo Por Tipo");

			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Tipo");
			headerRow.createCell(1).setCellValue("Tiempo Promedio");

			for (int f = 0; f < estadisticasTiempoPorTipo.size(); f++) {
				Row row = sheet.createRow(f + 1);
				Object[] estadistica = estadisticasTiempoPorTipo.get(f);
				BigDecimal tiempoTotalSegundos = BigDecimal.valueOf((Double) estadistica[1]);
				BigDecimal tiempoTotalHoras = tiempoTotalSegundos.divide(BigDecimal.valueOf(3600.0), 2, RoundingMode.HALF_UP);


				row.createCell(0).setCellValue(estadistica[0].toString());
				row.createCell(1).setCellValue(tiempoTotalHoras.doubleValue());
			}

			Drawing<?> drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 2, 0, 12, 15);

			XDDFChart chart = createBarChartTiempoDec(workbook, estadisticasTiempoPorTipo, anchor);
			XDDFChartLegend legend = chart.getOrAddLegend();
			legend.setPosition(LegendPosition.RIGHT);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=estadisticas_tiempo_por_tipo.xlsx");

			byte[] excelContent;
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				workbook.write(out);
				excelContent = out.toByteArray();
			} catch (IOException e) {
				throw new RuntimeException("Error al convertir el archivo Excel a bytes", e);
			}

			return ResponseEntity.ok().headers(responseHeaders).contentLength(excelContent.length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excelContent);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo Excel con las estadísticas de tiempo", e);
		}
	}

	private XDDFChart createBarChartTiempoDec(Workbook workbook, List<Object[]> data, ClientAnchor anchor) {
		XSSFDrawing drawing = (XSSFDrawing) workbook.getSheetAt(0).createDrawingPatriarch();
		XDDFChart chart = drawing.createChart(anchor);

		XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
		bottomAxis.setTitle("Tipos");

		XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
		leftAxis.setTitle("Tiempo Promedio");

		XDDFDataSource<String> category = XDDFDataSourcesFactory.fromStringCellRange((XSSFSheet) workbook.getSheetAt(0),
				new CellRangeAddress(1, data.size(), 0, 0));

		XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory
				.fromNumericCellRange((XSSFSheet) workbook.getSheetAt(0), new CellRangeAddress(1, data.size(), 1, 1));

		XDDFChartData data1 = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
		data1.setVaryColors(true);

		XDDFChartData.Series series = data1.addSeries(category, values);
		series.setTitle("Tiempo Promedio", null);

		chart.plot(data1);

		return chart;
	}

	@GetMapping(value = "/generar-excel-estadistica-tiempo-y-numero", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateExcelEstadisticaTiempoYNumero() {
		try {
			TypedQuery<Incidencia> query = entityManager.createQuery("SELECT i FROM Incidencia i", Incidencia.class);

			List<Incidencia> incidencias = query.getResultList();

			try (XSSFWorkbook workbook = new XSSFWorkbook()) {
				XSSFSheet sheet = workbook.createSheet("Estadisticas Tiempo");

				XSSFRow headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("Número de Incidencia");
				headerRow.createCell(1).setCellValue("Tiempo Dedicado");

				for (int f = 0; f < incidencias.size(); f++) {
					Row row = sheet.createRow(f + 1);
					Incidencia incidencia = incidencias.get(f);
					row.createCell(0).setCellValue(incidencia.getNum());
					Time tiempoDec = incidencia.getTiempo_dec();
					row.createCell(1).setCellValue(tiempoDec != null ? tiempoDec.toString() : "");

				}

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=estadisticas_tiempo_por_tipo.xlsx");

				byte[] excelContent;
				try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
					workbook.write(out);
					excelContent = out.toByteArray();
				} catch (IOException e) {
					throw new RuntimeException("Error al convertir el archivo Excel a bytes", e);
				}

				return ResponseEntity.ok().headers(responseHeaders).contentLength(excelContent.length)
						.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excelContent);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo Excel con las estadísticas de tiempo", e);
		}
	}

	@GetMapping(value = "/generar-excel-estadistica-tiempo-por-administrador", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateExcelEstadisticaTiempoPorAdministrador() {
		try {
			TypedQuery<Object[]> query = entityManager
					.createQuery("SELECT p, pr.perfil, SUM(TIME_TO_SEC(i.tiempo_dec)) AS tiempo_total "
							+ "FROM Incidencia i " + "JOIN i.personal1 p " + "JOIN Perfile pr ON p.id = pr.personalId "
							+ "WHERE pr.perfil = :perfil " + "GROUP BY p, pr.perfil", Object[].class);

			query.setParameter("perfil", Perfil.administrador);

			List<Object[]> estadisticasTiempoPorAdmin = query.getResultList();

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Estadisticas Tiempo Por Administrador");

			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Administrador");
			headerRow.createCell(1).setCellValue("Tiempo Total");

			for (int f = 0; f < estadisticasTiempoPorAdmin.size(); f++) {
				Row row = sheet.createRow(f + 1);
				Object[] estadistica = estadisticasTiempoPorAdmin.get(f);

				String nombre = ((Personal) estadistica[0]).getNombre();
				BigDecimal tiempoTotalSegundos = (BigDecimal) estadistica[2];
				BigDecimal tiempoTotalHoras = tiempoTotalSegundos.divide(BigDecimal.valueOf(3600.0), 2,
						RoundingMode.HALF_UP);

				row.createCell(0).setCellValue(nombre);
				row.createCell(1).setCellValue(tiempoTotalHoras.doubleValue());
			}

			Drawing<?> drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 2, 0, 12, 15);

			XDDFChart chart = createBarChartTiempoDec(workbook, sheet, estadisticasTiempoPorAdmin, anchor);
			XDDFChartLegend legend = chart.getOrAddLegend();
			legend.setPosition(LegendPosition.RIGHT);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=estadisticas_tiempo_por_administrador.xlsx");

			byte[] excelContent;
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				workbook.write(out);
				excelContent = out.toByteArray();
			} catch (IOException e) {
				throw new RuntimeException("Error al convertir el archivo Excel a bytes", e);
			}

			return ResponseEntity.ok().headers(responseHeaders).contentLength(excelContent.length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excelContent);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error al generar el archivo Excel con las estadísticas de tiempo por administrador", e);
		}
	}

	private XDDFChart createBarChartTiempoDec(XSSFWorkbook workbook, XSSFSheet sheet, List<Object[]> data,
											  ClientAnchor anchor) {
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		XDDFChart chart = drawing.createChart(anchor);

		XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
		bottomAxis.setTitle("Administrador");

		XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
		leftAxis.setTitle("Tiempo Total (horas)");

		List<String> administradores = new ArrayList<>();
		List<Double> tiemposTotalesHoras = new ArrayList<>();

		for (Object[] estadistica : data) {
			administradores.add(((Personal) estadistica[0]).getNombre());
			BigDecimal tiempoTotalSegundos = (BigDecimal) estadistica[2];
			double tiempoTotalHoras = tiempoTotalSegundos.doubleValue() / 3600.0;
			tiemposTotalesHoras.add(tiempoTotalHoras);
		}

		XDDFDataSource<String> category = XDDFDataSourcesFactory.fromArray(administradores.toArray(new String[0]));

		XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory
				.fromArray(tiemposTotalesHoras.toArray(new Double[0]));

		XDDFChartData data1 = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
		data1.setVaryColors(true);

		XDDFChartData.Series series = data1.addSeries(category, values);
		series.setTitle("Tiempo Total (horas)", null);

		chart.plot(data1);

		return chart;
	}

	@GetMapping(value = "/generar-excel-incidencias-por-administrador", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generateExcelIncidenciasPorAdministrador() {
		try {
			TypedQuery<Object[]> query = entityManager
					.createQuery("SELECT p.nombre, COUNT(i.num) AS cantidad_incidencias " + "FROM Incidencia i "
							+ "JOIN i.personal2 p " + "GROUP BY p.id, p.nombre", Object[].class);

			List<Object[]> incidenciasPorAdministrador = query.getResultList();

			try (XSSFWorkbook workbook = new XSSFWorkbook()) {
				XSSFSheet sheet = workbook.createSheet("Incidencias por Administrador");

				XSSFRow headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("Administrador");
				headerRow.createCell(1).setCellValue("Cantidad de Incidencias");

				for (int f = 0; f < incidenciasPorAdministrador.size(); f++) {
					Row row = sheet.createRow(f + 1);
					Object[] incidenciaInfo = incidenciasPorAdministrador.get(f);
					row.createCell(0).setCellValue(incidenciaInfo[0].toString());
					row.createCell(1).setCellValue(Integer.parseInt(incidenciaInfo[1].toString()));
				}

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=incidencias_por_administrador.xlsx");

				byte[] excelContent;
				try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
					workbook.write(out);
					excelContent = out.toByteArray();
				} catch (IOException e) {
					throw new RuntimeException("Error al convertir el archivo Excel a bytes", e);
				}

				return ResponseEntity.ok().headers(responseHeaders).contentLength(excelContent.length)
						.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excelContent);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo Excel con las incidencias por administrador", e);
		}
	}

	@GetMapping(value = "/generatepdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Resource> generatePDF() {
		List<Incidencia> lista = incidenciaService.getIncidenciasSinDTO();

		try {
			String name = "incidencias_" + UUID.randomUUID().toString() + ".pdf";
			String filePath = "./src/main/resources/incidencias.pdf";

			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);

				for (int f = 0; f < lista.size(); f++) {
					Incidencia incidencia = lista.get(f);
					contentStream.showText("Número: " + incidencia.getNum());
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Adjunto URL: " + incidencia.getAdjuntoUrl());
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Descripción: " + incidencia.getDescripcion());
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Estado: " + incidencia.getEstado());
					contentStream.newLineAtOffset(0, -15);

					contentStream.newLineAtOffset(0, -20);
				}

				contentStream.endText();
			}

			document.save(filePath);
			document.close();

			File file = new File(filePath);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);

			return ResponseEntity.ok().headers(responseHeaders).contentLength(file.length())
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo PDF", e);
		}
	}

	@GetMapping(value = "/generar-pdf-incidencias-abiertas", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Resource> generatePdfIncidenciasAbiertas() {
		List<Incidencia> lista = incidenciaService.getByEstado("abierta");

		try {
			String name = "incidencias_abiertas_" + UUID.randomUUID().toString() + ".pdf";
			String filePath = "./src/main/resources/incidencias_abiertas.pdf";

			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);

				for (int f = 0; f < lista.size(); f++) {
					Incidencia incidencia = lista.get(f);
					contentStream.showText("Número: " + incidencia.getNum());
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Adjunto URL: " + incidencia.getAdjuntoUrl());
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Descripción: " + incidencia.getDescripcion());
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Estado: " + incidencia.getEstado());
					contentStream.newLineAtOffset(0, -15);

					contentStream.newLineAtOffset(0, -20);
				}

				contentStream.endText();
			}

			document.save(filePath);
			document.close();

			File file = new File(filePath);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);

			return ResponseEntity.ok().headers(responseHeaders).contentLength(file.length())
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo PDF", e);
		}
	}

	@GetMapping(value = "/generar-pdf-porcentaje-tiempo", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Resource> generatePdfPorcentajeTiempo() {
		try {
			TypedQuery<Object[]> query = entityManager
					.createQuery("SELECT tipo, COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Incidencia) AS porcentaje "
							+ "FROM Incidencia GROUP BY tipo", Object[].class);

			List<Object[]> resultados = query.getResultList();

			String name = "porcentaje_tiempo_" + UUID.randomUUID().toString() + ".pdf";
			String filePath = "./src/main/resources/porcentaje_tiempo.pdf";

			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);

				for (Object[] resultado : resultados) {
					String tipoIncidencia = (String) resultado[0];
					Double porcentajeTiempo = (Double) resultado[1];

					contentStream.showText("Tipo de Incidencia: " + tipoIncidencia);
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Porcentaje de Tiempo: " + String.format("%.2f", porcentajeTiempo) + "%");
					contentStream.newLineAtOffset(0, -15);
					contentStream.newLineAtOffset(0, -20);
				}

				contentStream.endText();
			}

			document.save(filePath);
			document.close();

			File file = new File(filePath);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);

			return ResponseEntity.ok().headers(responseHeaders).contentLength(file.length())
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo PDF", e);
		}
	}

	@GetMapping(value = "/generate-pdf-tiempo-total", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Resource> generatePdfTiempoTotal() {
		try {
			String name = "tiempo_total_" + UUID.randomUUID().toString() + ".pdf";
			String filePath = "./src/main/resources/tiempo_total.pdf";

			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);

				TypedQuery<Object[]> query = entityManager.createQuery(
						"SELECT tipo, SEC_TO_TIME(SUM(TIME_TO_SEC(tiempo_dec))) FROM Incidencia GROUP BY tipo",
						Object[].class);

				List<Object[]> resultados = query.getResultList();

				for (Object[] resultado : resultados) {
					String tipo = (String) resultado[0];
					Time tiempoTotal = (Time) resultado[1];

					contentStream.showText("Tipo de Incidencia: " + tipo);
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Tiempo Total Dedicado: " + tiempoTotal.toString());
					contentStream.newLineAtOffset(0, -20);
				}

				contentStream.endText();
			}

			document.save(filePath);
			document.close();

			File file = new File(filePath);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);

			return ResponseEntity.ok().headers(responseHeaders).contentLength(file.length())
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo PDF", e);
		}
	}

	@GetMapping(value = "/generar-pdf-tiempo-incidencias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Resource> generatePdfTiempoIncidencias() {
		List<Incidencia> lista = incidenciaService.getIncidenciasSinDTO();

		try {
			String name = "tiempo_incidencias_" + UUID.randomUUID().toString() + ".pdf";
			String filePath = "./src/main/resources/tiempo_incidencias.pdf";

			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);

				for (int f = 0; f < lista.size(); f++) {
					Incidencia incidencia = lista.get(f);
					contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
					contentStream.showText("Número de Incidencia: " + incidencia.getNum());
					contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
					contentStream.newLineAtOffset(0, -15);
					Time tiempoDec = incidencia.getTiempo_dec();
					contentStream.showText("Tiempo Dedicado: " + (tiempoDec != null ? tiempoDec.toString() : ""));
					contentStream.newLineAtOffset(0, -20);
				}

				contentStream.endText();
			}

			document.save(filePath);
			document.close();

			File file = new File(filePath);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);

			return ResponseEntity.ok().headers(responseHeaders).contentLength(file.length())
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo PDF", e);
		}
	}

	@GetMapping(value = "/generate-pdf-incidencias-por-admin", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Resource> generatePdfIncidenciasPorAdmin() {
		try {
			String name = "incidencias_por_admin_" + UUID.randomUUID().toString() + ".pdf";
			String filePath = "./src/main/resources/incidencias_por_admin.pdf";

			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);

				TypedQuery<Object[]> query = entityManager
						.createQuery("SELECT p.nombre, COUNT(i.num) AS cantidad_incidencias " + "FROM Incidencia i "
								+ "JOIN i.personal2 p " + "GROUP BY p.id, p.nombre", Object[].class);

				List<Object[]> incidenciasPorAdministrador = query.getResultList();

				for (Object[] incidenciaInfo : incidenciasPorAdministrador) {
					String nombreAdmin = (String) incidenciaInfo[0];
					Long cantidadIncidencias = (Long) incidenciaInfo[1];

					contentStream.showText("Administrador: " + nombreAdmin);
					contentStream.newLineAtOffset(0, -15);
					contentStream.showText("Número de Incidencias: " + cantidadIncidencias.intValue());
					contentStream.newLineAtOffset(0, -15);
					contentStream.newLineAtOffset(0, -20);
				}

				contentStream.endText();
			}

			document.save(filePath);
			document.close();

			File file = new File(filePath);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);

			return ResponseEntity.ok().headers(responseHeaders).contentLength(file.length())
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} catch (Exception e) {
			throw new RuntimeException("Error al generar el archivo PDF", e);
		}
	}
}
