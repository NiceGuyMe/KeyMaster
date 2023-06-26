import React from "react";
import Input from "../../../components/auth/form/input";
import Layout from "../../../components/auth/form/layout";

const Signup = () => {
  return (
    <Layout isLogin={false}>
      <Input type={"mail"} placeholder={"john@doe.com"} label={"Your email"} />
      <Input
        type={"password"}
        placeholder={"*******"}
        label={"Your password"}
      />
    </Layout>
  );
};

export default Signup;
