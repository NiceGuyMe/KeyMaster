import React from "react";
import Input from "../../../components/auth/form/input";
import Layout from "../../../components/auth/form/layout";

const Login = () => {
  return (
    <Layout isLogin={true}>
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
