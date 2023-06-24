import React from "react";
import Input from "./form/input";
import Layout from "./form/layout";

const Login = () => {
  return (
    <Layout>
      <Input type={"mail"} placeholder={"john@doe.com"} label={"Your email"} />
      <Input
        type={"password"}
        placeholder={"*******"}
        label={"Your password"}
      />
    </Layout>
  );
};

export default Login;
