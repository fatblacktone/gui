/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jamp.gui;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class DList extends JList {
    private static final long serialVersionUID = 1L;

    public  static DataFlavor   DList_Flavor     = new DataFlavor( DListData.class, "DListData" );
    private static DataFlavor[] supportedFlavors = { DList_Flavor };
    private int dropIndex;

    public DList(){
        super();
        setTransferHandler( new ReorderHandler() );
        setDragEnabled( true );
        setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
    }

    public DList( DefaultListModel m ){
        this();
        setModel( m );
    }

    public int getDropIndex(){
        return dropIndex;
    }

    public void dropComplete(){}

    private class ReorderHandler extends TransferHandler {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean importData(TransferSupport support) {

            // this is the index of the element onto which the dragged element, is dropped
            dropIndex = DList.this.locationToIndex( getDropLocation().getDropPoint() );

            try{
                Object [] draggedData        = ((DListData)support.getTransferable().getTransferData( DList_Flavor )).data;
                final DList dragList        = ((DListData)support.getTransferable().getTransferData( DList_Flavor )).parent;
                DefaultListModel dragModel    = (DefaultListModel)dragList.getModel();
                DefaultListModel dropModel    = (DefaultListModel)DList.this.getModel();

                final Object leadItem     = dropIndex >= 0 ? dropModel.elementAt( dropIndex ) : null;
                final int dataLength     = draggedData.length;

                // make sure that the lead item, is not in the dragged data
                if( leadItem != null )
                    for( int i = 0 ; i < dataLength ; i++ )
                        if( draggedData[i].equals( leadItem ) )
                            return false;

                int dragLeadIndex        = -1;
                final boolean localDrop    = dropModel.contains( draggedData[0] );

                if( localDrop )
                    dragLeadIndex    = dropModel.indexOf( draggedData[0] );

                for( int i = 0 ; i < dataLength ; i++ )
                    dragModel.removeElement( draggedData[i] );

                if( localDrop ){
                    final int adjustedLeadIndex = dropModel.indexOf( leadItem );
                    final int insertionAdjustment = dragLeadIndex <= adjustedLeadIndex ? 1 : 0;

                    final int [] indices = new int[dataLength];
                    for( int i = 0 ; i < dataLength ; i++ ){
                        dropModel.insertElementAt( draggedData[i], adjustedLeadIndex + insertionAdjustment + i );
                        indices[i] = adjustedLeadIndex + insertionAdjustment + i;
                    }

                    SwingUtilities.invokeLater( new Runnable(){
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            DList.this.clearSelection();
                            DList.this.setSelectedIndices( indices );
                            dropComplete();
                        }
                    });
                }
                else{
                    final int [] indices = new int[dataLength];
                    for( int i = 0 ; i < dataLength ; i++ ){
                        dropModel.insertElementAt( draggedData[i], dropIndex + 1 );
                        indices[i] = dropIndex + 1 + i;
                    }

                    SwingUtilities.invokeLater( new Runnable(){
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            DList.this.clearSelection();
                            DList.this.setSelectedIndices( indices );
                            dragList.clearSelection();
                            dropComplete();
                        }
                    });
                }
            }
            catch( Exception x ){
                x.printStackTrace();
            }
            return false;
        }

        public int getSourceActions( JComponent c ){
            return TransferHandler.MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new DListData( DList.this, DList.this.getSelectedValues() );
        }

        @Override
        public boolean canImport(TransferSupport support) {
            if( !support.isDrop() || !support.isDataFlavorSupported( DList_Flavor ) )
                return false;


            return true;
        }

        @Override
        public Icon getVisualRepresentation(Transferable t) {
            // TODO Auto-generated method stub
            return super.getVisualRepresentation(t);
        }
    }

    private class DListData implements Transferable {

        private Object[]     data;
        private DList        parent;

        protected DListData( DList p, Object[] d ){
            parent    = p;
            data     = d;
        }

        @Override
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException, IOException {
            if ( flavor.equals( DList_Flavor ) )
                return DListData.this;
            else
                return null;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return supportedFlavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
