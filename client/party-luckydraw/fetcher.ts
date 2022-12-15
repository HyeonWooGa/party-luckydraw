import axios from "axios";
import { Dispatch, SetStateAction } from "react";
import { IParticipant } from "./pages";

export const getParticipants = async (
  setParticipants: Dispatch<SetStateAction<IParticipant[] | null>>
) => {
  try {
    const res = await axios.get("/users");
    setParticipants(res.data);
  } catch (error) {
    console.error(error);
  }
};

export const pushParticipants = async (
  username: string,
  phone: string,
  setUsername: Dispatch<SetStateAction<string>>,
  setPhone: Dispatch<SetStateAction<string>>,
  setRendering: Dispatch<SetStateAction<boolean>>
) => {
  try {
    await axios.post("/users", {
      username,
      phone,
    });
    setUsername("");
    setPhone("");
    setRendering((prev) => !prev);
  } catch (error) {
    console.error(error);
  }
};

export const doLuckyDraw = async (
  setPersonWonDraw: Dispatch<SetStateAction<string>>,
  setPhoneWonDraw: Dispatch<SetStateAction<string>>
) => {
  try {
    const res = await axios.get("/users/randoms");
    setPersonWonDraw(res.data.username);
    setPhoneWonDraw(res.data.phone);
  } catch (error) {
    console.error(error);
  }
};

export const deleteParticipant = async (
  userId: number,
  username: string,
  phone: string,
  setRendering: Dispatch<SetStateAction<boolean>>
) => {
  if (window.confirm(`${username} (${phone})님을 럭키드로우에서 제외합니까?`)) {
    try {
      await axios.delete(`/users/${userId}`);
      setRendering((prev) => !prev);
    } catch (error) {
      console.error(error);
    }
  }
};
