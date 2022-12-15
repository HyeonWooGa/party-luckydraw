import { NextPage } from "next";
import { FormEvent, useEffect, useState } from "react";
import {
  deleteParticipant,
  doLuckyDraw,
  getParticipants,
  pushParticipants,
} from "../fetcher";
import { motion, AnimatePresence } from "framer-motion";

export interface IParticipant {
  userId: number;
  username: string;
  phone: string;
}

const divVariants = {
  start: {
    opacity: 0,
    scale: 0.5,
  },
  end: {
    opacity: 1,
    scale: 1,
    transition: {
      type: "spring",
      duration: 1,
      bounce: 0.5,
      delayChildren: 1,
      staggerChildren: 1,
    },
  },
};

const spanVariants = {
  start: { opacity: 0, y: 10 },
  end: {
    opacity: 1,
    y: 0,
  },
};

const Home: NextPage = () => {
  const [participants, setParticipants] = useState<IParticipant[] | null>(null);
  const [username, setUsername] = useState("");
  const [phone, setPhone] = useState("");
  const [layoutId, setLayoutId] = useState("");
  const [personWonDraw, setPersonWonDraw] = useState("");
  const [phoneWonDraw, setPhoneWonDraw] = useState("");
  const [rendering, setRendering] = useState(true);

  useEffect(() => {
    getParticipants(setParticipants);
  }, [rendering]);

  const onSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    pushParticipants(username, phone, setUsername, setPhone, setRendering);
  };

  const onClickDraw = async () => {
    await doLuckyDraw(setPersonWonDraw, setPhoneWonDraw);
    setLayoutId("1");
  };

  const onClickDelete = (userId: number, userName: string, phone: string) => {
    deleteParticipant(userId, userName, phone, setRendering);
  };
  return (
    <motion.main className="w-[1080px] mx-auto my-8 flex flex-col gap-10">
      <section className="w-[1080px] py-8 bg-[#F2E5E5] rounded-xl flex justify-center">
        <h1 className="text-8xl">럭키드로우 참여</h1>
      </section>
      <form
        onSubmit={onSubmit}
        className="w-[1080px] py-8 bg-[#F2E5E5] rounded-xl px-8 flex flex-col gap-8 text-8xl"
      >
        <div className="flex items-center">
          <span className="w-[574px]">성함 :</span>
          <input
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="w-[440px] rounded-xl px-4 py-2"
            placeholder="성함"
            required
          />
        </div>
        <div className="flex items-center">
          <span className="w-[574px]">핸드폰 뒷번호 : </span>
          <input
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            className="w-[440px] rounded-xl px-4 py-2"
            placeholder="4자리수"
            required
          />
        </div>
        <button className="hidden">제출</button>
      </form>
      <section className="w-[1080px] py-8 bg-[#F2E5E5] rounded-xl flex justify-center">
        <div className="grid grid-cols-6 gap-8">
          {participants?.map((participant) => (
            <button
              onClick={() =>
                onClickDelete(
                  participant.userId,
                  participant.username,
                  participant.phone
                )
              }
              className="bg-[#E8C4C4] py-2 px-4 rounded-xl"
            >{`${participant.username} (${participant.phone})`}</button>
          ))}
        </div>
      </section>
      <motion.section
        layoutId={layoutId}
        className="w-[1080px] p-1 bg-[#F2E5E5] rounded-xl flex justify-center"
      >
        <button
          onClick={onClickDraw}
          className="text-8xl hover:bg-[#E8C4C4] py-7 w-full h-full"
        >
          럭키드로우 추첨
        </button>
      </motion.section>
      <AnimatePresence>
        {layoutId ? (
          <motion.div
            onClick={() => setLayoutId("")}
            initial={{ backgroundColor: "rgba(0,0,0,0)" }}
            animate={{ backgroundColor: "#2B3A55" }}
            exit={{ backgroundColor: "rgba(0,0,0,0)" }}
            className="w-screen h-screen fixed left-0 bottom-0 flex justify-center items-center"
          >
            <motion.section
              className="text-[#F2E5E5] flex flex-col gap-12 items-center"
              layoutId={layoutId}
            >
              <div className="text-4xl">축하합니다! 당첨자는!?</div>
              <motion.div variants={divVariants} initial="start" animate="end">
                {personWonDraw.split("").map((char) => (
                  <motion.span variants={spanVariants} className="text-8xl">
                    {char}
                  </motion.span>
                ))}
                <motion.span variants={spanVariants} className="text-8xl">
                  {` (${phoneWonDraw})`}
                </motion.span>
              </motion.div>
            </motion.section>
          </motion.div>
        ) : null}
      </AnimatePresence>
    </motion.main>
  );
};

export default Home;
