using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RecordLabel_WindowsFormsApp
{
    public partial class Form1 : Form
    {
        int selected_dj_id = -1;
        int selected_manager_id = -1;
        public Form1()
        {
            InitializeComponent();
            PopulateArtistManagerDataGridView();
        }
        SqlConnection conn;
        SqlCommand cmd;
        private void Form1_Load(object sender, EventArgs e) {
            conn = new SqlConnection("Data Source=localhost;Initial Catalog=Record_Label;Integrated Security=True");
            cmd = new SqlCommand();
            cmd.Connection = conn;
        }
        private void label1_Click(object sender, EventArgs e)
        {
            throw new System.NotImplementedException();
        }

        private void label2_Click(object sender, EventArgs e)
        {
            throw new System.NotImplementedException();
        }

        private void label3_Click(object sender, EventArgs e)
        {
            throw new System.NotImplementedException();
        }
        private void ClearData() {
            nick_name_textBox.Clear();
            full_name_textBox.Clear();
            fee_textBox.Clear();
        }

        private void PopulateArtistManagerDataGridView()
        {
            var select_managers = "SELECT * FROM ArtistManager";
            var conn = new SqlConnection("Data Source=localhost;Initial Catalog=Record_Label;Integrated Security=True"); 
            var dataAdapter = new SqlDataAdapter(select_managers, conn);
            
            var commandBuilder = new SqlCommandBuilder(dataAdapter);
            var data_set = new DataSet();
            dataAdapter.Fill(data_set);
            dataGridView_Artist_manager.ReadOnly = true; 
            dataGridView_Artist_manager.DataSource = data_set.Tables[0];
        }
        private void add_dj_Click(object sender, EventArgs e)
        {
            var conn = new SqlConnection("Data Source=localhost;Initial Catalog=Record_Label;Integrated Security=True");
            conn.Open();
            var cmd = new SqlCommand("insert into Dj values (@nick_name, @full_name,@fee, @manager_id)", conn);
            
            cmd.Parameters.AddWithValue("@nick_name", nick_name_textBox.Text);
            cmd.Parameters.AddWithValue("@full_name", full_name_textBox.Text);
            cmd.Parameters.AddWithValue("@fee", int.Parse(fee_textBox.Text));
            cmd.Parameters.AddWithValue("@manager_id", int.Parse(manager_id_textBox.Text));
            cmd.ExecuteNonQuery();
            ClearData();
            populateDJGridView();
            conn.Close();

            MessageBox.Show("Successfully Inserted");
            
        }

        private void populateDJGridView()
        {
            var conn = new SqlConnection("Data Source=localhost;Initial Catalog=Record_Label;Integrated Security=True");
            conn.Open();
            var cmd = new SqlCommand("SELECT * FROM Dj where manager_id = @artist_manager_id", conn);
            cmd.Parameters.AddWithValue("@artist_manager_id", selected_manager_id);
            
            
            DataTable dt = new DataTable();
            var adpter = new SqlDataAdapter(cmd);
            
            adpter.Fill(dt);
            dataGridView_djs.DataSource = dt;
            dataGridView_djs.ReadOnly = true;
            cmd.ExecuteNonQuery();
            conn.Close();
        }
        
        private void dataGridView_Artist_manager_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            dataGridView_Artist_manager.CurrentRow.Selected = true;
            selected_dj_id = int.Parse(dataGridView_Artist_manager.Rows[e.RowIndex].Cells["id"].Value.ToString());
            
            manager_id_textBox.Text = selected_dj_id.ToString();
            
            if (dataGridView_Artist_manager.SelectedRows.Count > 0)
            {
                groupBox1.Enabled = true;
            }
            var conn = new SqlConnection("Data Source=localhost;Initial Catalog=Record_Label;Integrated Security=True");
            conn.Open();
            var cmd = new SqlCommand("SELECT * FROM Dj where manager_id = @artist_manager_id", conn);
            cmd.Parameters.AddWithValue("@artist_manager_id", selected_dj_id);
            
            
            DataTable dt = new DataTable();
            var adpter = new SqlDataAdapter(cmd);
            
            adpter.Fill(dt);
            dataGridView_djs.DataSource = dt;
            dataGridView_djs.ReadOnly = true;
            cmd.ExecuteNonQuery();
            conn.Close();
        }

        private void update_dj_Click(object sender, EventArgs e)
        {
            var conn = new SqlConnection("Data Source=localhost;Initial Catalog=Record_Label;Integrated Security=True");
            conn.Open();
            var cmd = new SqlCommand(" UPDATE Dj SET nick_name=@dj_nick, full_name=@dj_full, fee=@dj_fee where id=@selected_dj_id", conn);
            cmd.Parameters.AddWithValue("@dj_nick", nick_name_textBox.Text);
            cmd.Parameters.AddWithValue("@dj_full", full_name_textBox.Text);
            cmd.Parameters.AddWithValue("@dj_fee", int.Parse(fee_textBox.Text));
            cmd.Parameters.AddWithValue("@selected_dj_id", selected_dj_id);
            
            cmd.ExecuteNonQuery();
            conn.Close();
            populateDJGridView();
            ClearData();
            
            MessageBox.Show("Successfully Updated");
        }

        private void delete_dj_Click(object sender, EventArgs e)
        {
            var conn = new SqlConnection("Data Source=localhost;Initial Catalog=Record_Label;Integrated Security=True");
            conn.Open();
            var cmd = new SqlCommand("DELETE FROM Dj where id = @dj_id", conn);
            cmd.Parameters.AddWithValue("@dj_id", selected_dj_id);

            
            cmd.ExecuteNonQuery();
            conn.Close();
            ClearData();
            populateDJGridView();
            
            MessageBox.Show("Successfully Deleted");
        }
        
        private void dataGridView_djs_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            dataGridView_djs.CurrentRow.Selected = true;
            int dj_id = int.Parse(dataGridView_djs.Rows[e.RowIndex].Cells["id"].Value.ToString());
            string dj_nick_name = dataGridView_djs.Rows[e.RowIndex].Cells["nick_name"].Value.ToString();
            string dj_full_name = dataGridView_djs.Rows[e.RowIndex].Cells["full_name"].Value.ToString();
            int dj_fee = int.Parse(dataGridView_djs.Rows[e.RowIndex].Cells["fee"].Value.ToString());

            selected_dj_id = dj_id;
            nick_name_textBox.Text = dj_nick_name;
            full_name_textBox.Text = dj_full_name;
            fee_textBox.Text = dj_fee.ToString();

        }
    }
}