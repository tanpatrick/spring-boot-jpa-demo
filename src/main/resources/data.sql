INSERT INTO posts(title, body, created_at) VALUES
    ('Sunt aut facere repellat provident', 'Quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut', CURRENT_TIMESTAMP()),
    ('Qui est esse', 'Dolorem eum magni eos aperiam quia', CURRENT_TIMESTAMP());

INSERT INTO comments(post_id, body, created_at) VALUES
    (1, 'Laudantium enim quasi est quidem magnam voluptate ipsam eos', CURRENT_TIMESTAMP()),
    (1, 'Est natus enim nihil est dolore omnis voluptatem numquam', CURRENT_TIMESTAMP());