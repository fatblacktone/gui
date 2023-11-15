package GuiComponents;

import backend.FileManipulation;
import backend.Level;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

public class TransferPanel extends JPanel implements MouseListener {

	ArrayListTransferHandler arrayListHandler;
	private LinkedList<Level> levelsOnThePc;
	private LinkedList<Level> levelsOnThePhone;
	private FileManipulation fileReader;
	private JList list1, list2;

	public TransferPanel() {
		levelsOnThePc = new LinkedList<Level>();
		levelsOnThePhone = new LinkedList<Level>();
		arrayListHandler = new ArrayListTransferHandler();
	}

	public void setFileReader(FileManipulation fileReader) {
		arrayListHandler.setFileReader(fileReader);
		this.fileReader = fileReader;
	}

	public void setup() {
		DefaultListModel list1Model = new DefaultListModel();
		levelsOnThePc = fileReader.getLevelsFromPc();
		for (int i = 0; i < levelsOnThePc.size(); i++) {
			list1Model.addElement(levelsOnThePc.get(i).getNameOfLevel());
		}
		list1 = new JList(list1Model);
		list1.setName("PC LEVELS");
		list1.addMouseListener(this);
		list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list1.setTransferHandler(arrayListHandler);

		list1.setDragEnabled(true);
		list1.setBackground(Color.LIGHT_GRAY);
		JScrollPane list1View = new JScrollPane(list1);
		list1View.setPreferredSize(new Dimension(160, 100));
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.GRAY);
		panel1.setLayout(new BorderLayout());
		panel1.add(list1View, BorderLayout.CENTER);
		panel1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		levelsOnThePhone = fileReader.getLevelsFromPhone();
		DefaultListModel list2Model = new DefaultListModel();
		for (int i = 0; i < levelsOnThePhone.size(); i++) {
			list2Model.addElement(levelsOnThePhone.get(i).getNameOfLevel());
		}
		list2 = new JList(list2Model);
		list2.addMouseListener(this);
		list2.setName("PHONE LEVELS");
		list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list2.setTransferHandler(arrayListHandler);
		list2.setDragEnabled(true);
		list2.setBackground(Color.LIGHT_GRAY);
		JScrollPane list2View = new JScrollPane(list2);
		list2View.setPreferredSize(new Dimension(160, 100));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(list2View, BorderLayout.CENTER);
		panel2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		panel2.setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		add(panel1, BorderLayout.LINE_START);
		add(panel2, BorderLayout.LINE_END);
		setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
	}

	public void mouseClicked(MouseEvent e) {
		Object[] values;
		if (e.getSource() == list2) {
			System.out.println("It got here!!");
			values = list2.getSelectedValues();
			for (int i = 0; i < values.length; i++) {
				Object o = values[i];
				String str = o.toString();

				int foundLevelIndex = fileReader.findPhoneLevelIndex(str);
				fileReader.removePhoneLevel(foundLevelIndex);

			}

		} else if (e.getSource() == list1) {
			values = list1.getSelectedValues();
			for (int i = 0; i < values.length; i++) {
				Object o = values[i];
				String str = o.toString();
				int foundLevelIndex = fileReader.findPcLevelIndex(str);
				fileReader.removePcLevel(foundLevelIndex);
			}
		}
		setup();
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	class ArrayListTransferHandler extends TransferHandler {
		DataFlavor localArrayListFlavor, serialArrayListFlavor;

		String localArrayListType = DataFlavor.javaJVMLocalObjectMimeType
				+ ";class=java.util.ArrayList";

		JList source = null;

		int[] indices = null;
		FileManipulation fileReader;

		int addIndex = -1; // Location where items were added

		int addCount = 0; // Number of items added
		private LinkedList<Level> levels;

		public ArrayListTransferHandler() {
			try {
				localArrayListFlavor = new DataFlavor(localArrayListType);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ArrayListTransferHandler: unable to create data flavor");
			}
			serialArrayListFlavor = new DataFlavor(ArrayList.class, "ArrayList");
			levels = new LinkedList<Level>();
		}

		public void setFileReader(FileManipulation fileReader) {
			this.fileReader = fileReader;
		}

		public boolean importData(JComponent c, Transferable t) {
			JList target = null;

			ArrayList alist = null;
			if (!canImport(c, t.getTransferDataFlavors())) {
				return false;
			}

			try {
				target = (JList) c;
				if (hasLocalArrayListFlavor(t.getTransferDataFlavors())) {
					alist = (ArrayList) t.getTransferData(localArrayListFlavor);
				} else if (hasSerialArrayListFlavor(t.getTransferDataFlavors())) {
					alist = (ArrayList) t
							.getTransferData(serialArrayListFlavor);
				} else {
					return false;
				}

			} catch (UnsupportedFlavorException ufe) {
				System.out.println("importData: unsupported data flavor");
				return false;
			} catch (IOException ioe) {
				System.out.println("importData: I/O exception");
				return false;
			}

			// At this point we use the same code to retrieve the data
			// locally or serially.

			// We'll drop at the current selected index.
			int index = target.getSelectedIndex();

			DefaultListModel listModel = (DefaultListModel) target.getModel();
			int max = listModel.getSize();
			if (index > max || index < 0) {
				index = max;
			}

			addIndex = index;
			addCount = alist.size();
			if (target.getName().equals("PC LEVELS")) {
				fileReader.addToPcLevels(levels, index);
			} else if (target.getName().equals("PHONE LEVELS")) {
				fileReader.addToPhoneLevels(levels, index);
			}
			for (int i = 0; i < alist.size(); i++) {

				listModel.add(index++, alist.get(i));
				target.setSelectedIndex(index);
			}
			for (int i = 0; i < fileReader.getLevelsFromPc().size(); i++) {
				System.out.println("PC LEVELS "
						+ fileReader.getLevelsFromPc().get(i).getNameOfLevel());
			}
			for (int i = 0; i < fileReader.getLevelsFromPhone().size(); i++) {
				System.out.println("PHONE LEVELS "
						+ fileReader.getLevelsFromPhone().get(i)
								.getNameOfLevel());
			}
			return true;
		}

		private boolean hasLocalArrayListFlavor(DataFlavor[] flavors) {
			if (localArrayListFlavor == null) {
				return false;
			}

			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].equals(localArrayListFlavor)) {
					return true;
				}
			}
			return false;
		}

		private boolean hasSerialArrayListFlavor(DataFlavor[] flavors) {
			if (serialArrayListFlavor == null) {
				return false;
			}

			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].equals(serialArrayListFlavor)) {
					return true;
				}
			}
			return false;
		}

		public boolean canImport(JComponent c, DataFlavor[] flavors) {
			if (hasLocalArrayListFlavor(flavors)) {
				return true;
			}
			if (hasSerialArrayListFlavor(flavors)) {
				return true;
			}
			return false;
		}

		protected Transferable createTransferable(JComponent c) {
			if (c instanceof JList) {
				source = (JList) c;
				levels = new LinkedList<Level>();
				DefaultListModel model = (DefaultListModel) source.getModel();
				// indices = source.getSelectedIndices();
				Object[] values = source.getSelectedValues();
				if (values == null || values.length == 0) {
					return null;
				}
				ArrayList alist = new ArrayList(values.length);
				for (int i = 0; i < values.length; i++) {
					Object o = values[i];
					String str = o.toString();
					if (str == null)
						str = "";
					alist.add(str);
					if (source.getName().equals("PC LEVELS")) {
						System.out.println("PC LEVELS");
						int foundLevelIndex = fileReader.findPcLevelIndex(str);
						levels.add(fileReader
								.getLevelFromThePc(foundLevelIndex));
						fileReader.removePcLevel(foundLevelIndex);
					} else if (source.getName().equals("PHONE LEVELS")) {
						System.out.println("PHONE LEVELS");
						int foundLevelIndex = fileReader
								.findPhoneLevelIndex(str);
						levels.add(fileReader
								.getLevelFromPhone(foundLevelIndex));
					}
					model.remove(model.indexOf(str));
					source = null;
				}
				return new ArrayListTransferable(alist);
			}
			return null;
		}

		public int getSourceActions(JComponent c) {
			return COPY_OR_MOVE;
		}

		public void actionPerformed(ActionEvent e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public class ArrayListTransferable implements Transferable {
			ArrayList data;

			public ArrayListTransferable(ArrayList alist) {
				data = alist;
			}

			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException {
				if (!isDataFlavorSupported(flavor)) {
					throw new UnsupportedFlavorException(flavor);
				}
				return data;
			}

			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { localArrayListFlavor,
						serialArrayListFlavor };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				if (localArrayListFlavor.equals(flavor)) {
					return true;
				}
				if (serialArrayListFlavor.equals(flavor)) {
					return true;
				}
				return false;
			}
		}
	}
}