package native_jdbc_programing.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import native_jdbc_programing.dao.impl.DepartmentDaoImpl;
import native_jdbc_programing.dto.Department;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentUi extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pDept;
	private JPanel pBtn;
	private JPanel pList;
	private JLabel lblDeptNo;
	private JTextField tfDeptno;
	private JLabel lblDeptName;
	private JTextField tfDeptName;
	private JLabel lblFloor;
	private JTextField tfFloor;
	private JButton btnAdd;
	private JButton btnCancel;
	private JScrollPane scrollPane;
	private JTable deptTable;
	private JButton btnModify;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentUi frame = new DepartmentUi();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DepartmentUi() {
		setTitle("부서정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pDept = new JPanel();
		contentPane.add(pDept);
		pDept.setLayout(new GridLayout(0, 2, 10, 0));

		lblDeptNo = new JLabel("부서 번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptNo);

		tfDeptno = new JTextField();
		pDept.add(tfDeptno);
		tfDeptno.setColumns(10);

		lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptName);

		tfDeptName = new JTextField();
		pDept.add(tfDeptName);
		tfDeptName.setColumns(10);

		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblFloor);

		tfFloor = new JTextField();
		pDept.add(tfFloor);
		tfFloor.setColumns(10);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnModify = new JButton("수정");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department updept = getDepartments();
				DepartmentDaoImpl.getInstance().updateDepartment(updept);
				deptTable.setModel(getModel());
				JOptionPane.showMessageDialog(null, "수정 완료");
			}
		});
		pBtn.add(btnModify);
		
				btnCancel = new JButton("취소");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				
				btnDelete = new JButton("삭제");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						DepartmentDaoImpl.getInstance().deleteDepartment(Integer.valueOf(tfDeptno.getText().trim()));
						
						deptTable.setModel(getModel());
						JOptionPane.showMessageDialog(null, "삭제 완료");
					}
				});
				pBtn.add(btnDelete);
				pBtn.add(btnCancel);

		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);

		deptTable = new JTable();

		deptTable.setModel(getModel());
		scrollPane.setViewportView(deptTable);
	}

	private TableModel getModel() {
		return new DefaultTableModel(getDepartment(), getColumnNames());
	}

	private Object[] getColumnNames() {
		return new String[] { "DeptNo", "DeptName", "Floor" };
	}

	public Object[][] getDepartment() {
		List<Department> list = DepartmentDaoImpl.getInstance().selectDepartmentByAll();
		Object[][] arr = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Department dept = list.get(i);
			arr[i] = new Object[] { dept.getDeptNo(), dept.getDeptName(), dept.getFloor() };
		}
		return arr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Department newdept = getDepartments();
		DepartmentDaoImpl.getInstance().insertDepartment(newdept);
		deptTable.setModel(getModel());
		JOptionPane.showMessageDialog(null, "추가 완료");
		clearTf();
	}

	private void clearTf() {
		tfDeptName.setText("");
		tfDeptno.setText("");
		tfFloor.setText("");
	}

	private Department getDepartments() {
		int deptNo = Integer.valueOf(tfDeptno.getText().trim());
		String deptName = String.valueOf(tfDeptName.getText().trim());
		int floor = Integer.valueOf(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

}
