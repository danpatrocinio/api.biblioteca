-- Executar no banco após todas as tabelas serem criadas:
-- ATENÇÃO: verificar cada entidade para mapear corretamente com @Column os nomes de todos os id's no singular para padronizarmos
ALTER TABLE livros ADD CONSTRAINT fk_generos FOREIGN KEY ( id_genero ) REFERENCES generos ( id_genero ) ;
ALTER TABLE livros ADD CONSTRAINT fk_autores FOREIGN KEY ( id_autor ) REFERENCES autores ( id_autor ) ;
ALTER TABLE livros ADD CONSTRAINT fk_editoras FOREIGN KEY ( id_editora ) REFERENCES editoras ( id_editora ) ;
ALTER TABLE funcionarios ADD CONSTRAINT fk_cargos FOREIGN KEY ( id_cargo ) REFERENCES cargos ( id_cargo ) ;
ALTER TABLE emprestimos ADD CONSTRAINT fk_funcionarios FOREIGN KEY ( id_funcionario ) REFERENCES funcionarios ( id_funcionario ) ;
ALTER TABLE emprestimos ADD CONSTRAINT fk_usuarios FOREIGN KEY ( id_usuario ) REFERENCES usuarios ( id_usuario ) ;
ALTER TABLE emprestimos ADD CONSTRAINT fk_livros1 FOREIGN KEY ( id_livro1 ) REFERENCES livros ( id_livro ) ;
ALTER TABLE emprestimos ADD CONSTRAINT fk_livros2 FOREIGN KEY ( id_livro2 ) REFERENCES livros ( id_livro ) ;
ALTER TABLE emprestimos ADD CONSTRAINT fk_livros3 FOREIGN KEY ( id_livro3 ) REFERENCES livros ( id_livro ) ;