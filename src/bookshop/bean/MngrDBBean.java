package bookshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MngrDBBean {
	// MngrDBBean 전역 객체 생성
	private static MngrDBBean instance = new MngrDBBean();

	// MngrDBBean 객체를 리턴하는 메소드
	public static MngrDBBean getInstance() {
		return instance;
	}

	private MngrDBBean() {

	}

	// 커넥션 풀에서 커넥션 객체를 얻어내는 메소드
	private Connection getConnection() throws Exception {
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		return ds.getConnection();
	}

	// 관리자 인증 메소드
	public int userCheck(String id, String passwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			con = getConnection();


			pstmt = con.prepareStatement("select managerPasswd from manager where managerId = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbpasswd = rs.getString("managerPasswd");
				if (dbpasswd.equals(passwd)) {
					x = 1;
				} else {
					x = 0;
				}
			} else {
				x = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}
		}
		return x;
	}

	// 책 등록 메소드
	public void insertBook(MngrDataBean book) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			String sql = "insert into book(book_kind, book_title, book_price, ";
			sql += "book_count, author, publishing_com, publishing_date, book_image, ";
			sql += "book_content, discount_rate, reg_date) values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getBook_kind());
			pstmt.setString(2, book.getBook_title());
			pstmt.setInt(3, book.getBook_price());
			pstmt.setInt(4, book.getBook_count());
			pstmt.setString(5, book.getAuthor());
			pstmt.setString(6, book.getPublishing_com());
			pstmt.setString(7, book.getPublishing_date());
			pstmt.setString(8, book.getBook_image());
			pstmt.setString(9, book.getBook_content());
			pstmt.setInt(10, book.getDiscount_rate());
			pstmt.setTimestamp(11, book.getReg_date());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}
		}
	}

	// 이미 등록된 책을 검증
	public int registedBookconfirm(String kind, String bookName, String author) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			con = getConnection();

			String sql = "select book_title from book ";
			sql += "where book_kind = ? and book_title = ? and author = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, bookName);
			pstmt.setString(3, author);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = 1;
			} else {
				x = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}
		}
		return x;
	}

	// 전체 등록된 책의 수를 얻어내는 메소드
	public int getBookCount() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("select count(*) from book");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}

		}
		return x;
	}

	// 해당 분류의 책의 수를 얻어내는 메소드
	public int getBookCount(String book_kind) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		int kind = Integer.parseInt(book_kind);

		try {
			con = getConnection();
			String query = "select count(*) from book where book_kind=" + kind;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}

		}
		return x;
	}

	// 책의 제목을 얻어냄
	public String getBookTitle(int book_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String x = "";

		try {
			con = getConnection();

			pstmt = con.prepareStatement("select book_title from book where book_id = " + book_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getString(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}

		}
		return x;
	}

	// 분류별 또는 전체 등록된 책의 정보를 얻어내는 메소드
	public List<MngrDataBean> getBooks(String book_kind) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MngrDataBean> bookList = null;

		try {
			con = getConnection();

			String sql1 = "select * from book";
			String sql2 = "select * from book";
			sql2 += "where book_kind = ? order by reg_date desc";

			if (book_kind.equals("all") || book_kind.equals("")) {
				pstmt = con.prepareStatement(sql1);
			} else {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, book_kind);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookList = new ArrayList<MngrDataBean>();
				do {
					MngrDataBean book = new MngrDataBean();

					book.setBook_id(rs.getInt("book_id"));
					book.setBook_kind(rs.getString("book_kind"));
					book.setBook_title(rs.getString("book_title"));
					book.setBook_price(rs.getInt("book_price"));
					book.setBook_count(rs.getInt("book_count"));
					book.setAuthor(rs.getString("author"));
					book.setPublishing_com(rs.getString("publishing_com"));
					book.setPublishing_date(rs.getString("publishing_date"));
					book.setBook_image(rs.getString("book_image"));
					book.setDiscount_rate(rs.getInt("discount_rate"));
					book.setReg_date(rs.getTimestamp("reg_date"));

					bookList.add(book);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}

		}
		return bookList;
	}

	// 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간책 목록을 얻어내는 메소드
	public MngrDataBean[] getBooks(String book_kind, int count) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MngrDataBean bookList[] = null;
		int i = 0;

		try {
			con = getConnection();

			String sql = "select * from book where book_kind = ? ";
			sql += "order by reg_date";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book_kind);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookList = new MngrDataBean[count];
				do {
					MngrDataBean book = new MngrDataBean();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_kind(rs.getString("book_kind"));
					book.setBook_title(rs.getString("book_title"));
					book.setBook_price(rs.getInt("book_price"));
					book.setBook_count(rs.getInt("book_count"));
					book.setAuthor(rs.getString("author"));
					book.setPublishing_com(rs.getString("publishing_com"));
					book.setPublishing_date(rs.getString("publishing_date"));
					book.setBook_image(rs.getString("book_image"));
					book.setDiscount_rate(rs.getInt("discount_rate"));
					book.setReg_date(rs.getTimestamp("reg_date"));

					bookList[i] = book;

					i++;
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}

		}
		return bookList;
	}

	// bookId에 해당하는 책의 정보를 얻어내는 메소드로
	// 등록된 책을 수정하기 위해 수정 폼으로 읽어들이기 위한 메소드
	public MngrDataBean getBook(int bookId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MngrDataBean book = null;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("select * from book where book_id = ?");
			pstmt.setInt(1, bookId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new MngrDataBean();

				book.setBook_id(rs.getInt("book_id"));
				book.setBook_kind(rs.getString("book_kind"));
				book.setBook_title(rs.getString("book_title"));
				book.setBook_price(rs.getInt("book_price"));
				book.setBook_count(rs.getInt("book_count"));
				book.setAuthor(rs.getString("author"));
				book.setPublishing_com(rs.getString("publishing_com"));
				book.setPublishing_date(rs.getString("publishing_date"));
				book.setBook_image(rs.getString("book_image"));
				book.setDiscount_rate(rs.getInt("discount_rate"));
				book.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}

		}
		return book;
	}
	
	// 등록된 책의 정보를 수정 시 사용하는 메소드
	public void updateBook(MngrDataBean book, int bookId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql; 
		
		try {
			con = getConnection();
			
			sql = "update book set book_kind=?, book_title=?, book_price=?";
			sql += ", book_count=?, author=?, publishing_com=?, publishing_date=?";
			sql += ", book_image=?, book_content=?, discount_rate=?";
			sql += " where book_id?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,  book.getBook_kind());
			pstmt.setString(2, book.getBook_title());
			pstmt.setInt(3, book.getBook_price());
			pstmt.setInt(4, book.getBook_count());
			pstmt.setString(5, book.getAuthor());
			pstmt.setString(6, book.getPublishing_com());
			pstmt.setString(7, book.getPublishing_date());
			pstmt.setString(8, book.getBook_image());
			pstmt.setString(9, book.getBook_content());
			pstmt.setInt(10, book.getDiscount_rate());
			pstmt.setInt(11, bookId);
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}
		}
	}
	
	// bookId에 해당하는 책의 정보를 삭제 시 사용하는 메소드
	public void deleteBook(int bookId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("delete from book where book_id=?");
			pstmt.setInt(1, bookId);
			
			pstmt.executeUpdate();
		}  catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}
		}
	}
}
