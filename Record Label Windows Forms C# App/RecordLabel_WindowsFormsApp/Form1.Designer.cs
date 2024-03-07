namespace RecordLabel_WindowsFormsApp
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }

            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.nick_name_textBox = new System.Windows.Forms.TextBox();
            this.full_name_textBox = new System.Windows.Forms.TextBox();
            this.fee_textBox = new System.Windows.Forms.TextBox();
            this.add_dj = new System.Windows.Forms.Button();
            this.update_dj = new System.Windows.Forms.Button();
            this.delete_dj = new System.Windows.Forms.Button();
            this.dataGridView_Artist_manager = new System.Windows.Forms.DataGridView();
            this.label6 = new System.Windows.Forms.Label();
            this.dataGridView_djs = new System.Windows.Forms.DataGridView();
            this.label7 = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.label5 = new System.Windows.Forms.Label();
            this.manager_id_textBox = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView_Artist_manager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView_djs)).BeginInit();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(5, 42);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(100, 23);
            this.label2.TabIndex = 1;
            this.label2.Text = "Nick Name:";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(5, 76);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(93, 21);
            this.label3.TabIndex = 2;
            this.label3.Text = "Full Name:";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // label4
            // 
            this.label4.Location = new System.Drawing.Point(5, 108);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(100, 23);
            this.label4.TabIndex = 3;
            this.label4.Text = "Fee:";
            // 
            // nick_name_textBox
            // 
            this.nick_name_textBox.Location = new System.Drawing.Point(155, 39);
            this.nick_name_textBox.Name = "nick_name_textBox";
            this.nick_name_textBox.Size = new System.Drawing.Size(167, 22);
            this.nick_name_textBox.TabIndex = 6;
            // 
            // full_name_textBox
            // 
            this.full_name_textBox.Location = new System.Drawing.Point(155, 73);
            this.full_name_textBox.Name = "full_name_textBox";
            this.full_name_textBox.Size = new System.Drawing.Size(167, 22);
            this.full_name_textBox.TabIndex = 7;
            // 
            // fee_textBox
            // 
            this.fee_textBox.Location = new System.Drawing.Point(155, 105);
            this.fee_textBox.Name = "fee_textBox";
            this.fee_textBox.Size = new System.Drawing.Size(167, 22);
            this.fee_textBox.TabIndex = 8;
            // 
            // add_dj
            // 
            this.add_dj.Location = new System.Drawing.Point(432, 65);
            this.add_dj.Name = "add_dj";
            this.add_dj.Size = new System.Drawing.Size(96, 39);
            this.add_dj.TabIndex = 10;
            this.add_dj.Text = "Add";
            this.add_dj.UseVisualStyleBackColor = true;
            this.add_dj.Click += new System.EventHandler(this.add_dj_Click);
            // 
            // update_dj
            // 
            this.update_dj.Location = new System.Drawing.Point(534, 65);
            this.update_dj.Name = "update_dj";
            this.update_dj.Size = new System.Drawing.Size(96, 39);
            this.update_dj.TabIndex = 11;
            this.update_dj.Text = "Update";
            this.update_dj.UseVisualStyleBackColor = true;
            this.update_dj.Click += new System.EventHandler(this.update_dj_Click);
            // 
            // delete_dj
            // 
            this.delete_dj.Location = new System.Drawing.Point(636, 65);
            this.delete_dj.Name = "delete_dj";
            this.delete_dj.Size = new System.Drawing.Size(96, 39);
            this.delete_dj.TabIndex = 12;
            this.delete_dj.Text = "Delete Dj";
            this.delete_dj.UseVisualStyleBackColor = true;
            this.delete_dj.Click += new System.EventHandler(this.delete_dj_Click);
            // 
            // dataGridView_Artist_manager
            // 
            this.dataGridView_Artist_manager.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView_Artist_manager.Location = new System.Drawing.Point(12, 67);
            this.dataGridView_Artist_manager.Name = "dataGridView_Artist_manager";
            this.dataGridView_Artist_manager.RowTemplate.Height = 24;
            this.dataGridView_Artist_manager.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridView_Artist_manager.Size = new System.Drawing.Size(820, 200);
            this.dataGridView_Artist_manager.TabIndex = 14;
            this.dataGridView_Artist_manager.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView_Artist_manager_CellClick);
            // 
            // label6
            // 
            this.label6.Font = new System.Drawing.Font("MS Reference Sans Serif", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(302, 9);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(263, 42);
            this.label6.TabIndex = 15;
            this.label6.Text = "Artist Managers:";
            this.label6.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // dataGridView_djs
            // 
            this.dataGridView_djs.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView_djs.Location = new System.Drawing.Point(12, 352);
            this.dataGridView_djs.Name = "dataGridView_djs";
            this.dataGridView_djs.RowTemplate.Height = 24;
            this.dataGridView_djs.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridView_djs.Size = new System.Drawing.Size(820, 170);
            this.dataGridView_djs.TabIndex = 16;
            this.dataGridView_djs.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView_djs_CellClick);
            // 
            // label7
            // 
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(12, 307);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(601, 42);
            this.label7.TabIndex = 17;
            this.label7.Text = "Djs managed by the selected manager:";
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.manager_id_textBox);
            this.groupBox1.Controls.Add(this.delete_dj);
            this.groupBox1.Controls.Add(this.update_dj);
            this.groupBox1.Controls.Add(this.add_dj);
            this.groupBox1.Controls.Add(this.fee_textBox);
            this.groupBox1.Controls.Add(this.full_name_textBox);
            this.groupBox1.Controls.Add(this.nick_name_textBox);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Enabled = false;
            this.groupBox1.Location = new System.Drawing.Point(14, 540);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(777, 183);
            this.groupBox1.TabIndex = 18;
            this.groupBox1.TabStop = false;
            // 
            // label5
            // 
            this.label5.Location = new System.Drawing.Point(6, 144);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(100, 23);
            this.label5.TabIndex = 14;
            this.label5.Text = "Manager Id:";
            // 
            // manager_id_textBox
            // 
            this.manager_id_textBox.Location = new System.Drawing.Point(155, 145);
            this.manager_id_textBox.Name = "manager_id_textBox";
            this.manager_id_textBox.ReadOnly = true;
            this.manager_id_textBox.Size = new System.Drawing.Size(167, 22);
            this.manager_id_textBox.TabIndex = 13;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Control;
            this.ClientSize = new System.Drawing.Size(846, 907);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.dataGridView_djs);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.dataGridView_Artist_manager);
            this.Location = new System.Drawing.Point(15, 15);
            this.Name = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView_Artist_manager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView_djs)).EndInit();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.TextBox manager_id_textBox;
        private System.Windows.Forms.Label label5;

        private System.Windows.Forms.GroupBox groupBox1;

        private System.Windows.Forms.Label label7;

        private System.Windows.Forms.DataGridView dataGridView_djs;

        private System.Windows.Forms.DataGridView dataGridView_Artist_manager;

        // private System.Windows.Forms.DataGridView dataGridView1;

        private System.Windows.Forms.Button update_dj;
        private System.Windows.Forms.Button delete_dj;
        private System.Windows.Forms.Label label6;

        private System.Windows.Forms.Button add_dj;

        private System.Windows.Forms.TextBox nick_name_textBox;
        private System.Windows.Forms.TextBox full_name_textBox;
        private System.Windows.Forms.TextBox fee_textBox;

        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;

        #endregion
    }
}