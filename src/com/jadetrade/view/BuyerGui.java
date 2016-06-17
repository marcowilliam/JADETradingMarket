package com.jadetrade.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;

public class BuyerGui {

	protected Shell shell;
	private CCombo combo_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BuyerGui window = new BuyerGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		final CCombo combo = new CCombo(shell, SWT.BORDER);
		combo.setBounds(80, 79, 256, 20);
		String[] carros = {"Sedã","SUV","Conversivel","Picape"};
		combo.setItems(carros);
		
		combo_1 = new CCombo(shell, SWT.BORDER);
		combo_1.setBounds(80, 158, 256, 20);
		String[] tiposDePagamento = {"A vista", "Parcelado"};
		combo_1.setItems(tiposDePagamento);
		
		Label lblSelecioneOTipo = new Label(shell, SWT.NONE);
		lblSelecioneOTipo.setBounds(80, 55, 256, 14);
		lblSelecioneOTipo.setText("Selecione o tipo de carro que deseja comprar:");
		
		Label lblOlComprador = new Label(shell, SWT.NONE);
		lblOlComprador.setBounds(180, 10, 97, 14);
		lblOlComprador.setText("Olá comprador!");
		
		Label lblSelecioneAForma = new Label(shell, SWT.NONE);
		lblSelecioneAForma.setBounds(80, 131, 197, 14);
		lblSelecioneAForma.setText("Selecione a forma de pagamento:");
		
		Button btnProcurar = new Button(shell, SWT.NONE);
		btnProcurar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int carroSelecionadoIndex = combo.getSelectionIndex();
				int formaDePagamentoIndex = combo_1.getSelectionIndex();
				
				if (carroSelecionadoIndex == -1 || formaDePagamentoIndex == -1){
					Label lblSelecioneUmCarro = new Label(shell, SWT.NONE);
					lblSelecioneUmCarro.setBounds(95, 254, 287, 14);
					lblSelecioneUmCarro.setText("*Selecione um carro e uma forma de pagamento");
				}
				else{
					String carroSelecionado = combo.getItem(carroSelecionadoIndex);
					String formaDePagamento = combo_1.getItem(formaDePagamentoIndex);
					System.out.println(carroSelecionado);
					System.out.println(formaDePagamento);
				}
			}
		});
		btnProcurar.setBounds(180, 204, 94, 28);
		btnProcurar.setText("Procurar");
		
		
		

	}
}
