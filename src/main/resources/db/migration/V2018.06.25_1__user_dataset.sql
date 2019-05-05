do $$
BEGIN
  for i in 1..20000000 LOOP
    INSERT INTO hello_world.users (SELECT i, 'user' || i, 'surname' || i , floor(random() * 10 + 1)::int);
  END LOOP;
end;
$$

